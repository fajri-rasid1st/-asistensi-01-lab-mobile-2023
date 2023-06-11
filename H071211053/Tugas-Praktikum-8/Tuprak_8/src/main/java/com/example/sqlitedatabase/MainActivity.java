package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fabAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabAdd = findViewById(R.id.fab_add);
        List<Student> studentList = getAllStudentNoteFromDatabase();
        recyclerView = findViewById(R.id.rv_note);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StudentAdapter favoriteAdapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(favoriteAdapter);

        fabAdd.setOnClickListener(view -> {
            Toast.makeText(this, "Add Favorite", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, FormActivity.class);
            intent.putExtra("add", "test");
            startActivity(intent);
            finish();
        });
    }

    private List<Student> getAllStudentNoteFromDatabase() {
        List<Student> studentList = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getAllStudents();

        if (cursor != null && cursor.moveToFirst()) {

            int idColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry._ID);
            int nameColum = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_NAME);
            int nimColum = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_NIM);
            int hobbyColum = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_HOBBY);

            do {
                int id = (idColumnIndex != -1) ? cursor.getInt(idColumnIndex) : -1;
                String nama = (nameColum != -1) ? cursor.getString(nameColum) : null;
                String nim = (nimColum != -1) ? cursor.getString(nimColum) : null;
                String hobby = (hobbyColum != -1) ? cursor.getString(hobbyColum) : null;

                Student favorite = new Student(id, nama, nim, hobby);
                studentList.add(favorite);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return studentList;
    }
}