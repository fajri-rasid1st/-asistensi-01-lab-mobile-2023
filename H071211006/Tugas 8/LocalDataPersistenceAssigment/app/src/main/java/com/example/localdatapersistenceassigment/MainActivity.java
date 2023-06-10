package com.example.localdatapersistenceassigment;

import static com.example.localdatapersistenceassigment.FormActivity.EXTRA_NOTES;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnAdd;
    private SearchView searchView;
    private RecyclerView rvNotes;
    private Notes notes;
    private TextView tvEmptyMessage;
    private static List<Notes> notesList;
    private NotesHelper notesHelper;
    private static NotesAdapter notesAdapter;

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getData() != null) {
            switch (result.getResultCode()) {
                case FormActivity.RESULT_ADD:
                    notes = result.getData().getParcelableExtra(FormActivity.EXTRA_NOTES);
                    showCurrentUser(notes);
                    Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
                    break;
                case FormActivity.RESULT_UPDATE:
                    notes = result.getData().getParcelableExtra(FormActivity.EXTRA_NOTES);
                    showCurrentUser(notes);
                    Toast.makeText(this, "Item edited successfully", Toast.LENGTH_SHORT).show();
                    break;
                case FormActivity.RESULT_DELETE:
//                    notes = null;
//                    showCurrentUser(notes);
                    Toast.makeText(this, "Item removed successfully", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchView);
        rvNotes = findViewById(R.id.rv_notes);
        btnAdd = findViewById(R.id.btn_add);

        notesHelper = NotesHelper.getInstance(getApplicationContext());
        notesHelper.open();

        notesList = new ArrayList<>();
        notesAdapter = new NotesAdapter(notesList, note -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            intent.putExtra(EXTRA_NOTES, notes);
            resultLauncher.launch(intent);
        });
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setAdapter(notesAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchData(newText);
                return true;
            }
        });

        btnAdd.setOnClickListener(view -> {
            Intent toForm = new Intent(MainActivity.this, FormActivity.class);
            resultLauncher.launch(toForm);
        });
    }

    private void loadNotes() {
        new LoadNotesAsync(this, notess -> {
            runOnUiThread(() -> {
                notesList.clear();
                notesList.addAll(notess);
                notesAdapter.notifyDataSetChanged();
                if (notess.size() > 0) {
                    notes = notess.get(0);
                } else {
                    notes = null;
                }
                showCurrentUser(notes);
            });
        }).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();

    }

    private void searchData(CharSequence charSequence) {
        rvNotes.setVisibility(View.GONE);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String query = charSequence.toString().toLowerCase().trim();
            ArrayList<Notes> searchedNotes = new ArrayList<>();

            if (!TextUtils.isEmpty(query)) {
                for (Notes note : notesList) {
                    String title = note.getTitle().toLowerCase();
                    if (title.contains(query)) {
                        searchedNotes.add(note);
                    }
                }
            } else {
                loadNotes();
            }

            handler.post(() -> {
                notesAdapter.updateList(searchedNotes);
                rvNotes.setVisibility(View.VISIBLE);
            });
        });
    }

    private void showCurrentUser(Notes notes) {
        tvEmptyMessage = findViewById(R.id.tv_emptyMessage);
        if (notes != null) {
            tvEmptyMessage.setVisibility(View.GONE);
            searchView.setVisibility(View.VISIBLE);
        } else {
            tvEmptyMessage.setText("There are no notes yet, please add first");
            tvEmptyMessage.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.GONE);
        }
    }

    private static class LoadNotesAsync extends AsyncTask<Void, Void, ArrayList<Notes>> {
        private final WeakReference<Context> weakContext;
        private final LoadNotesCallback callback;

        private LoadNotesAsync(Context context, LoadNotesCallback callback) {
            weakContext = new WeakReference<>(context);
            this.callback = callback;
        }

        @Override
        protected ArrayList<Notes> doInBackground(Void... voids) {
            Context context = weakContext.get();
            NotesHelper noteHelper = NotesHelper.getInstance(context);
            noteHelper.open();
            Cursor notesCursor = noteHelper.queryAll();
            ArrayList<Notes> notes = MappingHelper.mapCursorToArrayList(notesCursor);
            noteHelper.close();
            return notes;
        }

        @Override
        protected void onPostExecute(ArrayList<Notes> notes) {
            callback.postExecute(notes);
        }
    }

    interface LoadNotesCallback {
        void postExecute(ArrayList<Notes> notes);
    }
}