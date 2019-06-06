package com.example.piechartfinal.Database;

import android.provider.BaseColumns;

public class DbSchema {
    private static final String TAG = "DbSchema";

    public static final String DATABASE_NAME = "spinner.db";
    public static final int DATABASE_VERSION = 1;
    public static final String SORT_ASC = " ASC";
    public static final String SORT_DESC = " DESC";
    public static final String[] ORDERS = {SORT_ASC,SORT_DESC};
    public static final int OFF = 0;
    public static final int ON = 1;

    public static final class Table_Spinner implements BaseColumns  { 
        // Table Name
        public static final String TABLE_NAME = "spinner";

        // Table Columns
        public static final String COL_ID = "ID";
        public static final String COL_MOOD = "MOOD";
        public static final String COL_COUNTER = "COUNTER";
        public static final String COL_USED = "USED";
        public static final String COL_RATING = "RATING";

        // Create Table Statement
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS spinner ( " + 
            COL_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,  " + 
            COL_MOOD + " TEXT," + 
            COL_COUNTER + " INTEGER DEFALUT NULL," + 
            COL_USED + " INTEGER," + 
            COL_RATING + " INTEGER );";

        // Drop table statement
        public static final String DROP_TABLE = "DROP TABLE IF EXISTS spinner;";

        // Columns list array
        public static final String[] allColumns = {
            COL_ID,
            COL_MOOD,
            COL_COUNTER,
            COL_USED,
            COL_RATING };


    }

    public static final class Table_Dates implements BaseColumns  { 
        // Table Name
        public static final String TABLE_NAME = "dates";

        // Table Columns
        public static final String COL_ID = "ID";
        public static final String COL_DATE_NOW = "DATE_NOW";

        // Create Table Statement
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS dates ( " + 
            COL_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,  " + 
            COL_DATE_NOW + " TEXT );";

        // Drop table statement
        public static final String DROP_TABLE = "DROP TABLE IF EXISTS dates;";

        // Columns list array
        public static final String[] allColumns = {
            COL_ID,
            COL_DATE_NOW };
    }

}
