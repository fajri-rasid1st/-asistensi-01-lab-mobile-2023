package com.example.tprak8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tprak8.database.DatabaseContract;
import com.example.tprak8.database.NoteHelper;
import com.example.tprak8.model.NoteModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteActivity extends AppCompatActivity {

    public static final String EXTRA_NOTE = "extra_note";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private NoteHelper noteHelper;
    private boolean isEdit = false;
    private TextInputEditText tietTitle, tietDescription;
    private NoteModel note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        MaterialButton btnAdd = findViewById(R.id.btn_add);
        MaterialButton btnRemove = findViewById(R.id.btn_remove);
        tietTitle = findViewById(R.id.tiet_title);
        tietDescription = findViewById(R.id.tiet_description);

        // menginisiasi note helper
        noteHelper = NoteHelper.getInstance(getApplicationContext());

        // mengambil intent objek
        note = getIntent().getParcelableExtra(EXTRA_NOTE);

        // membuat database yang writable
        noteHelper.open();

        if (note != null) {

            //jika note ada isi maka akan jalan proses update dan delete
            isEdit = true;
            tietTitle.setText(note.getTitle());
            tietDescription.setText(note.getDescription());

            btnRemove.setVisibility(View.VISIBLE);
            btnAdd.setText(getString(R.string.save));
            btnAdd.setOnClickListener(v -> updateNote());
            btnRemove.setOnClickListener(v -> deleteNote());
        } else {

            //jika note tidak ada isi maka akan jalan proses create
            note = new NoteModel();
            btnAdd.setText(getString(R.string.add));
            btnAdd.setOnClickListener(v -> createNote());
        }
    }

    private void deleteNote() {

        // note akan dihapus berdasarkan id dan akan mengirim RESULT_DELETE lalu kembali ke
        // MainActivity
        long result = noteHelper.deleteById(String.valueOf(note.getId()));

        if (result > 0) {

            Intent intent = new Intent();
            setResult(RESULT_DELETE, intent);
            finish();
        } else {

            Snackbar.make(findViewById(android.R.id.content),
                    getString(R.string.failed_to_remove), Snackbar.LENGTH_SHORT).show();
        }
    }

    private void createNote() {

        String title = tietTitle.getText().toString().trim();

        if (title.isEmpty()) {

            tietTitle.setError(getString(R.string.title_required));
            return;
        }

        ContentValues values = getContentValues(title);
        long result = noteHelper.insert(values);

        if (result > 0) {

            note.setId((int) result);
            Intent intent = new Intent();
            intent.putExtra(EXTRA_NOTE, note);
            setResult(RESULT_ADD, intent);
            finish();
        } else {

            Snackbar.make(findViewById(android.R.id.content),
                    getString(R.string.failed_to_add), Snackbar.LENGTH_SHORT).show();
        }
    }

    private void updateNote() {

        String title = tietTitle.getText().toString().trim();

        if (title.isEmpty()) {

            tietTitle.setError(getString(R.string.title_required));
            return;
        }

        ContentValues values = getContentValues(title);
        long result = noteHelper.update(String.valueOf(note.getId()), values);

        if (result > 0) {

            Intent intent = new Intent();
            intent.putExtra(EXTRA_NOTE, note);
            setResult(RESULT_UPDATE, intent);
            finish();
        } else {

            Snackbar.make(findViewById(android.R.id.content),
                    getString(R.string.failed_to_edit), Snackbar.LENGTH_SHORT).show();
        }
    }

    private ContentValues getContentValues(String title) {
        String description = tietDescription.getText().toString().trim();
        String date;

        // jika note sudah ada maka created at berubah menjadi edited at
        if (isEdit) {
            date = getString(R.string.edited_at) + " " + getFormattedDate();
        } else {
            date = getString(R.string.created_at) + " " + getFormattedDate();
        }

        note.setTitle(title);
        note.setDescription(description);
        note.setDate(date);

        // membuat values dari semua variabel dan dikembalikan
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.NoteColumns.TITLE, title);
        values.put(DatabaseContract.NoteColumns.DESCRIPTION, description);
        values.put(DatabaseContract.NoteColumns.DATE, date);

        return values;
    }

    private String getFormattedDate() {

        // memformat tanggal dan waktu
        Date currentDate = new Date();
        String pattern = "dd/MM/yyyy HH:mm";
        DateFormat df = new SimpleDateFormat(pattern, Locale.getDefault());

        return df.format(currentDate);
    }
}
