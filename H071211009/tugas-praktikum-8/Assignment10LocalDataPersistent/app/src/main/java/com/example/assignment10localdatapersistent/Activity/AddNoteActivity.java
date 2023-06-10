package com.example.assignment10localdatapersistent.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment10localdatapersistent.DatabaseContract;
import com.example.assignment10localdatapersistent.ItemHelper;
import com.example.assignment10localdatapersistent.Model.NoteModel;
import com.example.assignment10localdatapersistent.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddNoteActivity extends AppCompatActivity {
    public static final int RESULT_ADD = 100;
    public static final int RESULT_UPDATE = 200;
    public static final int RESULT_DELETE = 300;
    public static final String EXTRA_ITEM = "extra_item";
    private EditText etTitle, etDesc;
    private Button btnSubmit, btnDelete;
    private boolean isEdit = false;
    private ItemHelper itemHelper;
    private NoteModel item;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        setView();

        itemHelper = ItemHelper.getInstance(getApplicationContext());
        itemHelper.open();

        item = getIntent().getParcelableExtra(EXTRA_ITEM);

        // Cek apakah item ada, jika ada berarti dalam mode edit
        if (item != null) {
            isEdit = true;
        } else {
            item = new NoteModel();
        }

        if (isEdit) {
            btnSubmit.setText("Update");
            btnDelete.setVisibility(Button.VISIBLE);
            if (item != null) {
                etTitle.setText(item.getName());
                etDesc.setText(item.getDescription());
            }
        } else {
            btnSubmit.setText("Submit");
        }

        btnSubmit.setOnClickListener(v -> addUpdate());
        btnDelete.setOnClickListener(v -> delete());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addUpdate() {
        String title = etTitle.getText().toString().trim();
        String description = etDesc.getText().toString().trim();

        // Validasi input
        if (title.isEmpty()) {
            etTitle.setError("Title cannot be empty");
            return;
        }

        item.setName(title);
        item.setDescription(description);

        String created_date = getDateOrTime("yyyy/MM/dd");
        String created_time = getDateOrTime("HH:mm:ss");

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.ItemColumns.DESCRIPTION, description);
        contentValues.put(DatabaseContract.ItemColumns.NAME, title);
        contentValues.put(DatabaseContract.ItemColumns.CREATED_DATE, created_date);
        contentValues.put(DatabaseContract.ItemColumns.CREATED_TIME, created_time);

        Intent intent = new Intent();

        Log.d("AddNoteActivity", "addUpdate: " + created_date + " " + created_time);

        if (isEdit) {
            // Jika dalam mode edit, update data ke database
            long result = itemHelper.updateData(String.valueOf(item.getId()), contentValues);
            if (result > 0) {
                item.setId((int) result);
                setResult(RESULT_UPDATE, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Jika dalam mode tambah, tambahkan data ke database
            long result = itemHelper.insertData(contentValues);
            if (result > 0) {
                item.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDateOrTime(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private void delete() {
        Intent intent = new Intent();

        // Hapus data dari database
        long result = itemHelper.deleteData(String.valueOf(item.getId()));

        if (result > 0) {
            setResult(RESULT_DELETE, intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("WrongViewCast")
    private void setView() {
        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnDelete = findViewById(R.id.btnDelete);
    }
}