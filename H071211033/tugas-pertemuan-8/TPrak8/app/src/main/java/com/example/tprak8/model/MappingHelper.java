package com.example.tprak8.model;

import android.database.Cursor;

import com.example.tprak8.database.DatabaseContract;

import java.util.ArrayList;

public class MappingHelper {

    // mencocokan value dari kolom tabel database ke objek NoteModel
    public static ArrayList<NoteModel> mapCursorToArrayList(Cursor cursor) {

        ArrayList<NoteModel> notes = new ArrayList<>();

        while (cursor.moveToNext()) {

            int id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
            String title =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE));
            String description =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION));
            String date =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE));

            notes.add(new NoteModel(id, title, description, date));
        }

        return notes;
    }
}
