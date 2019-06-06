package com.example.piechartfinal.Database;

import java.util.ArrayList;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

public class DatesDao extends DbManager {
    private static final String TAG = "DatesDao";

    protected static SQLiteDatabase database;
    protected static DbManager mDbManager;
    protected static  String[] allColumns = DbSchema.Table_Dates.allColumns;


    protected DatesDao() {
    }

    protected static void database_open() throws SQLException {
        mDbManager = getsInstance();
        database = mDbManager.getDatabase();
    }

    protected static void database_close() {
        mDbManager = getsInstance();
        mDbManager.close();
    }

    public static Dates loadRecordById(int mID)  { 
        database_open();
        Cursor cursor = database.query(DbSchema.Table_Dates.TABLE_NAME,allColumns,  "ID = ?" , new String[] { String.valueOf(mID) } , null, null, null,null);

        if (cursor != null)
            cursor.moveToFirst();

        Dates dates = new Dates();
        dates = cursorToDates(cursor);

        cursor.close();
        database_close();

        return dates;
    }

    public static ArrayList<Dates> loadAllRecords() {
        ArrayList<Dates> datesList = new ArrayList<Dates>();
        database_open();

        Cursor cursor = database.query(
                DbSchema.Table_Dates.TABLE_NAME,
                allColumns,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Dates dates = cursorToDates(cursor);
            datesList.add(dates);
            cursor.moveToNext();
        }
        cursor.close();
        database_close();
        return datesList;
    }

    // Please always use the typed column names (Table_Dates) when passing arguments.
    // Example: Table_Dates.Column_Name
    public static ArrayList<Dates> loadAllRecords(String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        ArrayList<Dates> datesList = new ArrayList<Dates>();
        database_open();

        if(TextUtils.isEmpty(selection)){
            selection = null;
            selectionArgs = null;
        }

        Cursor cursor = database.query(
                DbSchema.Table_Dates.TABLE_NAME,
                allColumns,
                selection==null ? null : selection,
                selectionArgs==null ? null : selectionArgs,
                groupBy==null ? null : groupBy,
                having==null ? null : having,
                orderBy==null ? null : orderBy);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Dates dates = cursorToDates(cursor);
            datesList.add(dates);
            cursor.moveToNext();
        }
        cursor.close();
        database_close();
        return datesList;
    }

    public static long insertRecord(Dates dates) {
        ContentValues values = new ContentValues();
        values = getDatesValues(dates);
        database_open();
        long insertId = database.insert(DbSchema.Table_Dates.TABLE_NAME , null, values);
        database_close();
        return insertId;
    }

    public static int updateRecord(Dates dates) { 
        ContentValues values = new ContentValues();
        values = getDatesValues(dates);
        database_open();
        String[] where = new String[] { String.valueOf(dates.getID()) }; 
        int updatedId = database.update(DbSchema.Table_Dates.TABLE_NAME , values, DbSchema.Table_Dates.COL_ID + " = ? ",where );
        database_close();
        return updatedId;
    }

    public static int deleteRecord(Dates dates) { 
        database_open();
        String[] where = new String[] { String.valueOf(dates.getID()) }; 
        int deletedCount = database.delete(DbSchema.Table_Dates.TABLE_NAME , DbSchema.Table_Dates.COL_ID + " = ? ",where );
        database_close();
        return deletedCount;
    }

    public static int deleteRecord(String id) {
        database_open();
        String[] where = new String[] { id }; 
        int deletedCount = database.delete(DbSchema.Table_Dates.TABLE_NAME , DbSchema.Table_Dates.COL_ID + " = ? ",where );
        database_close();
        return deletedCount;
    }

    public static int deleteAllRecords() {
        database_open();
        int deletedCount = database.delete(DbSchema.Table_Dates.TABLE_NAME , null, null );
        database_close();
        return deletedCount;
    }

    protected static ContentValues getDatesValues(Dates dates) {
        ContentValues values = new ContentValues();

        values.put(DbSchema.Table_Dates.COL_ID, dates.getID());
        values.put(DbSchema.Table_Dates.COL_DATE_NOW, dates.getDATE_NOW());

        return values;
    }

    protected static Dates cursorToDates(Cursor cursor)  {
        Dates dates = new Dates();

        dates.setID(cursor.getInt(cursor.getColumnIndex(DbSchema.Table_Dates.COL_ID)));
        dates.setDATE_NOW(cursor.getString(cursor.getColumnIndex(DbSchema.Table_Dates.COL_DATE_NOW)));

        return dates;
    }

    //my own

    public static int maxDId(){
        database_open();
        String query = "SELECT MAX("+DbSchema.Table_Dates.COL_ID+") FROM "+DbSchema.Table_Dates.TABLE_NAME;
        Cursor cursor  = database.rawQuery(query,null);
        int maxID=-1;



        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
            if (cursor != null)
            {
                maxID = cursor.getInt(0);
            }
        }
        cursor.close();
        database_close();

        if(maxID != -1)
            return maxID+1;
        else
            return 0;
    }

}

