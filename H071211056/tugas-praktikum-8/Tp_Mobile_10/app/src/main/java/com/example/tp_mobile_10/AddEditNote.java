package com.example.tp_mobile_10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddEditNote extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private Button btnSave, btnDelete;
    private NoteDatabase database;
    private long id;
    private Intent intent;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        intent = getIntent();

        id = intent.getLongExtra(NoteDatabase.id_note,0);
        if(intent.hasExtra(NoteDatabase.id_note)){
            setTitle("Update Note");
        }else {
            setTitle("Add Note");
        }

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        database = new NoteDatabase(this);
        
        btnSave.setOnClickListener(view -> saveProcess());

        btnDelete.setOnClickListener(view -> deleteProcess());

        getNote();
    }


    @SuppressLint("Range")
    private void getNote() {
        Cursor cursor = database.getNote(id);
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndex(NoteDatabase.title));
            String description = cursor.getString(cursor.getColumnIndex(NoteDatabase.description));

            etTitle.setText(title);
            etDescription.setText(description);
        }
    }

    private void saveProcess() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        String time = String.format("Created at " + "%04d-%02d-%02d %02d:%02d:%02d", year, month, dayOfMonth, hours, minutes, seconds);

        if (title.isEmpty()) {
            etTitle.setError("Field can not be blank");
        } else {
            ContentValues values = new ContentValues();
            values.put(NoteDatabase.title, title);
            values.put(NoteDatabase.description, description);
            values.put(NoteDatabase.time, time);



            if (intent.hasExtra(NoteDatabase.id_note)) {
                database.updateNote(values, id);
                Toast.makeText(this, "Item edited successfully", Toast.LENGTH_SHORT).show();
            }else {
                database.insertNote(values);
                Toast.makeText(this, "Item add successfully", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    private void deleteProcess() {
        database.deleteNote(id);
        Toast.makeText(this, "Item removed successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}