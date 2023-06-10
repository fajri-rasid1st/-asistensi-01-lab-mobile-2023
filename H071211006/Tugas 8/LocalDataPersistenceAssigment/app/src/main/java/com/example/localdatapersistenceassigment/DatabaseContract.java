package com.example.localdatapersistenceassigment;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "notes";

    public static final class NotesColumns implements BaseColumns {
        public static String TITLE = "title";
        public static String DETAIL = "detail";
        public static String DESCRIPTION = "desc";
    }
}