package com.example.localdatapersistenceassigment;

import android.database.Cursor;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Notes> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Notes> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.TITLE));
            String detail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.DETAIL));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.DESCRIPTION));
            notes.add(new Notes(id, title, detail, desc));
        }
        return notes;
    }
}