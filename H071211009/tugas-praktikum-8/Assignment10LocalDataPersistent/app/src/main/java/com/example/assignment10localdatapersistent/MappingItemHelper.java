package com.example.assignment10localdatapersistent;

import android.database.Cursor;
import com.example.assignment10localdatapersistent.Model.NoteModel;
import java.util.ArrayList;

public class MappingItemHelper {
    public static ArrayList<NoteModel> getAllItemArrayList(Cursor cursor) {
        ArrayList<NoteModel> noteModels = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ItemColumns._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ItemColumns.NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ItemColumns.DESCRIPTION));
            String created_date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ItemColumns.CREATED_DATE));
            String created_time = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ItemColumns.CREATED_TIME));

            noteModels.add(new NoteModel(id, name, description, created_date, created_time));
        }
        return noteModels;
    }
}