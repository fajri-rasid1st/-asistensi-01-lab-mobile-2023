package com.example.tprak8;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.tprak8.adapter.NoteAdapter;
import com.example.tprak8.database.DatabaseContract;
import com.example.tprak8.model.MappingHelper;
import com.example.tprak8.database.NoteHelper;
import com.example.tprak8.model.NoteModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private NoteHelper noteHelper;
    private Cursor cursor;
    private ArrayList<NoteModel> notes;
    private RecyclerView rvNotes;
    private NoteAdapter noteAdapter;
    private String searchText = "";
    private ExecutorService executor;
    private Handler handler;

    //Menampilkan Snackbar sesuai dengan CRUD yang telah terjadi
    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getData() != null) {
                            switch (result.getResultCode()) {
                                case NoteActivity.RESULT_ADD:

                                    Snackbar.make(findViewById(android.R.id.content),
                                            getString(R.string.note_added), Snackbar.LENGTH_SHORT).show();
                                    break;

                                case NoteActivity.RESULT_UPDATE:

                                    Snackbar.make(findViewById(android.R.id.content),
                                            getString(R.string.note_edited), Snackbar.LENGTH_SHORT).show();
                                    break;

                                case NoteActivity.RESULT_DELETE:

                                    Snackbar.make(findViewById(android.R.id.content),
                                            getString(R.string.note_deleted), Snackbar.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // membuat executor untuk proses background dan handler untuk proses UI
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        TextInputEditText tietSearch = findViewById(R.id.tiet_search);
        rvNotes  =findViewById(R.id.rv_notes);
        ExtendedFloatingActionButton extendedFab = findViewById(R.id.extended_fab);

        rvNotes.setHasFixedSize(true);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));

        // extendedFab melakukan intent ke NoteActivity yang akan mengembalikan result setelah
        // aktivitas selesai
        extendedFab.setOnClickListener(v -> {

            Intent intent = new Intent(this, NoteActivity.class);
            resultLauncher.launch(intent);
        });

        // fungsi yang akan berjalan saat ada perubahan text pada tietSearch dengan memasukan value
        // ke searchText, lalu diubah ke string dan akan melakukan method updateUI
        tietSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchText = s.toString().trim().toLowerCase();
                updateUI();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Saat kembali ke MainActivity, UI akan terupdate agar memunculkan data terbaru
    @Override
    protected void onResume() {

        super.onResume();

        updateUI();
    }

    private void updateUI() {

        // proses akan berjalan di background
        executor.execute(() -> {

            noteHelper = NoteHelper.getInstance(this);
            noteHelper.open();

            // query like akan berjalan di background dengan mengambil value searchText
            // saat like kosong maka akan melakukan query all atau select *
            String selection = DatabaseContract.NoteColumns.TITLE + " LIKE ? OR " +
                    DatabaseContract.NoteColumns.DESCRIPTION + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + searchText + "%", "%" + searchText + "%"};
            cursor = NoteHelper.getDatabase().query(
                    NoteHelper.getDatabaseTable(),
                    null,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    DatabaseContract.NoteColumns._ID + " ASC"
            );

            // memasukan value dari kolom database ke atribut NoteModel
            notes = MappingHelper.mapCursorToArrayList(cursor);

            noteHelper.close();

            //menampilkan RecyclerView
            handler.post(() -> {
                noteAdapter = new NoteAdapter(notes, resultLauncher);
                rvNotes.setAdapter(noteAdapter);
            });
        });
    }
}

