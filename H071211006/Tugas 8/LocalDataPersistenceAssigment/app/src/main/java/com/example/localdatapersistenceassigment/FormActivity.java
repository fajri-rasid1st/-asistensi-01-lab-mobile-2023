package com.example.localdatapersistenceassigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {
    public static final String EXTRA_NOTES = "extra_notes";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private NotesHelper notesHelper;
    private Notes notes;
    private EditText etTitle, etDesc;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etTitle = findViewById(R.id.et_title);
        etDesc = findViewById(R.id.et_desc);
        Button btnSubmit = findViewById(R.id.btn_submit);
        Button btnDelete = findViewById(R.id.btn_delete);

        notesHelper = NotesHelper.getInstance(getApplicationContext());
        notesHelper.open();
        notes = getIntent().getParcelableExtra(EXTRA_NOTES);

        if (notes != null) {
            isEdit = true;
        } else {
            notes = new Notes();
        }
        String buttonTitle;
        if (isEdit) {
            buttonTitle = "Update";
            if (notes != null) {
                etTitle.setText(notes.getTitle());
                etDesc.setText(notes.getDesc());
            }
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            buttonTitle = "Submit";
        }
        btnSubmit.setText(buttonTitle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btnSubmit.setOnClickListener(view -> submit());
        btnDelete.setOnClickListener(view -> delete());
    }

    private void submit() {
        String title = etTitle.getText().toString().trim();
        String desc = etDesc.getText().toString().trim();
        if (title.isEmpty()) {
            etTitle.setError("Field can not be blank");
            return;
        }
        if (desc.isEmpty()) {
            etDesc.setError("Field can not be blank");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        notes.setTitle(title);
        notes.setDesc(desc);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOTES, notes);
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.NotesColumns.TITLE, title);
        if (isEdit) {
            values.put(DatabaseContract.NotesColumns.DETAIL, "Edited at " + currentDate);
        } else {
            values.put(DatabaseContract.NotesColumns.DETAIL, "Created at " + currentDate);
        }

        values.put(DatabaseContract.NotesColumns.DESCRIPTION, desc);
        if (isEdit) {
            long result = notesHelper.update(String.valueOf(notes.getId()), values);
            if (result > 0) {
                setResult(RESULT_UPDATE, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to edit data", Toast.LENGTH_SHORT).show();
            }
        } else {
            long result = notesHelper.insert(values);
            if (result > 0) {
                notes.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete() {
        long result = notesHelper.deleteById(String.valueOf(notes.getId()));
        if (result > 0) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_NOTES, notes);
            setResult(RESULT_DELETE, intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to remove data", Toast.LENGTH_SHORT).show();
        }
    }
}