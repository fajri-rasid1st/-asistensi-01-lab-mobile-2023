package com.example.tprak8.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    // membuat tabel dan kolom database
    public static String TABLE_NAME = "note";

    public static final class NoteColumns implements BaseColumns {

        public static String TITLE = "title";
        public static String DESCRIPTION = "description";
        public static String DATE = "date";
    }
}
