package com.example.localdatapersistenceassigment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "Notes.db";
    private static final int DATABASE_VERSION = 1;

//    public static final String _ID = "id";
//    public static final String TITLE = "title";
//    public static final String DETAIL = "detail";
//    public static final String DESCRIPTION = "description";
//
    private static final String SQL_CREATE_TABLE_NOTES = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " %s TEXT NOT NULL,"
                    + " %s TIMESTAMP DEFAULT (DATETIME('NOW', 'LOCALTIME')),"
                    + " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_NAME,
            DatabaseContract.NotesColumns._ID,
            DatabaseContract.NotesColumns.TITLE,
            DatabaseContract.NotesColumns.DETAIL,
            DatabaseContract.NotesColumns.DESCRIPTION);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}