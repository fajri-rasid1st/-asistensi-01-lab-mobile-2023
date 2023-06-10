package com.example.assignment10localdatapersistent.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment10localdatapersistent.Adapter.NoteAdapter;
import com.example.assignment10localdatapersistent.ItemHelper;
import com.example.assignment10localdatapersistent.MappingItemHelper;
import com.example.assignment10localdatapersistent.Model.NoteModel;
import com.example.assignment10localdatapersistent.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private EditText etSearch;
    private TextView tvNoNote;
    private ImageButton ibAdd;
    private RecyclerView rvNote;
    private ArrayList<NoteModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setView();
        LoadAllItems(this);

        ibAdd.setOnClickListener(v -> {
            // Menampilkan AddNoteActivity saat tombol add di klik
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            resultLauncher.launch(intent);
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            // Listener untuk meng-handle perubahan teks pada search bar
            @Override
            public void afterTextChanged(Editable s) {
                // Memanggil LoadAllItems() jika search query kosong, jika tidak, memanggil LoadSearchedItems()
                if (s.toString().isEmpty()) {
                    LoadAllItems(MainActivity.this);
                } else {
                    LoadSearchedItems(MainActivity.this, s.toString());
                }
            }

            // Metode ini tidak digunakan, tetapi harus diimplementasikan
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            // Metode ini tidak digunakan, tetapi harus diimplementasikan
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getData() != null) {
                    LoadAllItems(this);
                    String toastMessage = "";

                    switch (result.getResultCode()) {
                        case AddNoteActivity.RESULT_ADD:
                            toastMessage = "Item added successfully";
                            break;
                        case AddNoteActivity.RESULT_UPDATE:
                            toastMessage = "Item updated successfully";
                            break;
                        case AddNoteActivity.RESULT_DELETE:
                            toastMessage = "Item removed successfully";
                            break;
                    }
                    // Menampilkan pesan toast berdasarkan hasil dari AddNoteActivity
                    Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
                }
            });

    private void LoadAllItems(Context context) {
        new LoadAsyncItem(context, itemsDump -> {
            items = itemsDump;
            // Menampilkan atau menyembunyikan pesan "No notes" berdasarkan jumlah item yang dimuat
            tvNoNote.setVisibility(itemsDump.isEmpty() ? TextView.VISIBLE : TextView.GONE);
            showAllItems(items);
        }).execute();
    }

    private void LoadSearchedItems(Context context, String s) {
        new LoadAsyncItem(context, itemsDump -> {
            items = itemsDump;
            showAllItems(items);
        }, s).searchExecute();
    }

    private void setView() {
        etSearch = findViewById(R.id.etSearch);
        tvNoNote = findViewById(R.id.tvNoNote);
        rvNote = findViewById(R.id.rvNote);
        ibAdd = findViewById(R.id.ibAdd);
    }

    private void showAllItems(ArrayList<NoteModel> NoteModels) {
        rvNote.setHasFixedSize(true);
        rvNote.setLayoutManager(new LinearLayoutManager(this));
        // Menginisialisasi dan mengatur adapter untuk RecyclerView
        NoteAdapter noteAdapter = new NoteAdapter(NoteModels, resultLauncher);
        rvNote.setAdapter(noteAdapter);
    }

    interface LoadCallback {
        // Callback yang dipanggil setelah proses load selesai
        void postExecute(ArrayList<NoteModel> items);
    }

    private static class LoadAsyncItem {
        private final Context context;
        private final LoadCallback callback;
        private String searchQuery;

        public LoadAsyncItem(Context context, LoadCallback callback) {
            this.context = context;
            this.callback = callback;
        }

        public LoadAsyncItem(Context context, LoadCallback callback, String searchQuery) {
            this.context = context;
            this.callback = callback;
            this.searchQuery = searchQuery;
        }

        void execute() {
            Executor executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                ItemHelper itemHelper = ItemHelper.getInstance(context);
                itemHelper.open();
                // Mendapatkan cursor dari database
                Cursor itemCursor = itemHelper.queryShowAll();
                // Mapping cursor menjadi ArrayList dari NoteModel
                ArrayList<NoteModel> NoteModels = MappingItemHelper.getAllItemArrayList(itemCursor);
                itemHelper.close();
                // Memanggil callback untuk mengirim hasil load
                handler.post(() -> callback.postExecute(NoteModels));
            });
        }

        void searchExecute() {
            Executor executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                ItemHelper itemHelper = ItemHelper.getInstance(context);
                itemHelper.open();
                // Mendapatkan cursor dari database berdasarkan search query
                Cursor itemCursor = itemHelper.queryShowByName(searchQuery);
                // Mapping cursor menjadi ArrayList dari NoteModel
                ArrayList<NoteModel> NoteModels = MappingItemHelper.getAllItemArrayList(itemCursor);
                itemHelper.close();
                // Memanggil callback untuk mengirim hasil load
                handler.post(() -> callback.postExecute(NoteModels));
            });
        }
    }
}