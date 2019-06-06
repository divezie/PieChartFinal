package com.example.piechartfinal.Database;

import java.util.ArrayList;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

public class SpinnerDao extends DbManager {
    private static final String TAG = "SpinnerDao";

    protected static SQLiteDatabase database;
    protected static DbManager mDbManager;
    protected static  String[] allColumns = DbSchema.Table_Spinner.allColumns;


    protected SpinnerDao() {
    }

    protected static void database_open() throws SQLException {
        mDbManager = getsInstance();
        database = mDbManager.getDatabase();
    }

    protected static void database_close() {
        mDbManager = getsInstance();
        mDbManager.close();
    }

    public static Spinner loadRecordById(int mID)  { 
        database_open();
        Cursor cursor = database.query(DbSchema.Table_Spinner.TABLE_NAME,allColumns,  "ID = ?" , new String[] { String.valueOf(mID) } , null, null, null,null);

        if (cursor != null)
            cursor.moveToFirst();

        Spinner spinner = new Spinner();
        spinner = cursorToSpinner(cursor);

        cursor.close();
        database_close();

        return spinner;
    }

    public static ArrayList<Spinner> loadAllRecords() {
        ArrayList<Spinner> spinnerList = new ArrayList<Spinner>();
        database_open();

        Cursor cursor = database.query(
                DbSchema.Table_Spinner.TABLE_NAME,
                allColumns,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Spinner spinner = cursorToSpinner(cursor);
            spinnerList.add(spinner);
            cursor.moveToNext();
        }
        cursor.close();
        database_close();
        return spinnerList;
    }

    // Please always use the typed column names (Table_Spinner) when passing arguments.
    // Example: Table_Spinner.Column_Name
    public static ArrayList<Spinner> loadAllRecords(String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        ArrayList<Spinner> spinnerList = new ArrayList<Spinner>();
        database_open();

        if(TextUtils.isEmpty(selection)){
            selection = null;
            selectionArgs = null;
        }

        Cursor cursor = database.query(
                DbSchema.Table_Spinner.TABLE_NAME,
                allColumns,
                selection==null ? null : selection,
                selectionArgs==null ? null : selectionArgs,
                groupBy==null ? null : groupBy,
                having==null ? null : having,
                orderBy==null ? null : orderBy);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Spinner spinner = cursorToSpinner(cursor);
            spinnerList.add(spinner);
            cursor.moveToNext();
        }
        cursor.close();
        database_close();
        return spinnerList;
    }

    public static long insertRecord(Spinner spinner) {
        ContentValues values = new ContentValues();
        values = getSpinnerValues(spinner);
        database_open();
        long insertId = database.insert(DbSchema.Table_Spinner.TABLE_NAME , null, values);
        database_close();
        return insertId;
    }

    public static int updateRecord(Spinner spinner) { 
        ContentValues values = new ContentValues();
        values = getSpinnerValues(spinner);
        database_open();
        String[] where = new String[] { String.valueOf(spinner.getID()) }; 
        int updatedId = database.update(DbSchema.Table_Spinner.TABLE_NAME , values, DbSchema.Table_Spinner.COL_ID + " = ? ",where );
        database_close();
        return updatedId;
    }

    public static int deleteRecord(Spinner spinner) { 
        database_open();
        String[] where = new String[] { String.valueOf(spinner.getID()) }; 
        int deletedCount = database.delete(DbSchema.Table_Spinner.TABLE_NAME , DbSchema.Table_Spinner.COL_ID + " = ? ",where );
        database_close();
        return deletedCount;
    }

    public static int deleteRecord(String id) {
        database_open();
        String[] where = new String[] { id }; 
        int deletedCount = database.delete(DbSchema.Table_Spinner.TABLE_NAME , DbSchema.Table_Spinner.COL_ID + " = ? ",where );
        database_close();
        return deletedCount;
    }

    public static int deleteAllRecords() {
        database_open();
        int deletedCount = database.delete(DbSchema.Table_Spinner.TABLE_NAME , null, null );
        database_close();
        return deletedCount;
    }

    protected static ContentValues getSpinnerValues(Spinner spinner) {
        ContentValues values = new ContentValues();

        values.put(DbSchema.Table_Spinner.COL_ID, spinner.getID());
        values.put(DbSchema.Table_Spinner.COL_MOOD, spinner.getMOOD());
        values.put(DbSchema.Table_Spinner.COL_COUNTER, spinner.getCOUNTER());
        values.put(DbSchema.Table_Spinner.COL_USED, spinner.getUSED());
        values.put(DbSchema.Table_Spinner.COL_RATING, spinner.getRATING());

        return values;
    }

    protected static Spinner cursorToSpinner(Cursor cursor)  {
        Spinner spinner = new Spinner();

        spinner.setID(cursor.getInt(cursor.getColumnIndex(DbSchema.Table_Spinner.COL_ID)));
        spinner.setMOOD(cursor.getString(cursor.getColumnIndex(DbSchema.Table_Spinner.COL_MOOD)));
        spinner.setCOUNTER(cursor.getInt(cursor.getColumnIndex(DbSchema.Table_Spinner.COL_COUNTER)));
        spinner.setUSED(cursor.getInt(cursor.getColumnIndex(DbSchema.Table_Spinner.COL_USED)));
        spinner.setRATING(cursor.getInt(cursor.getColumnIndex(DbSchema.Table_Spinner.COL_RATING)));

        return spinner;
    }

//my own
    public static ArrayList<Integer> loadMoodCounter(){
        database_open();
        String counterQuery = "SELECT "+ DbSchema.Table_Spinner.COL_COUNTER +" FROM "+ DbSchema.Table_Spinner.TABLE_NAME ;

        Cursor  cursor = database.rawQuery(counterQuery,null);


        ArrayList<Integer> theCounts = new ArrayList<>();


        if (cursor != null) {
            while (cursor.moveToNext()) {
                theCounts.add(cursor.getInt(0));
            }

        }




        cursor.close();
        database_close();

        return theCounts;
    }

    public static ArrayList<String> loadMoodName(){
        database_open();

        String query = "SELECT "+DbSchema.Table_Spinner.COL_MOOD+" FROM " +DbSchema.Table_Spinner.TABLE_NAME;

        Cursor cursor = database.rawQuery(query,null);


        ArrayList<String> theMoods = new ArrayList<>();


        if (cursor != null) {
            while(cursor.moveToNext()) {
                theMoods.add(cursor.getString(0));

            }
        }




        cursor.close();
        database_close();
        return theMoods;
    }


    public static int getCounter(int id) {
        database_open();
        String query1 = "SELECT " + DbSchema.Table_Spinner.COL_COUNTER + " FROM " + DbSchema.Table_Spinner.TABLE_NAME +
                " WHERE " + DbSchema.Table_Spinner.COL_ID + " == " +id;
        Cursor cursor = database.rawQuery(query1, null);
        int count = 0, i = 0;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            if (cursor != null) {
                count = cursor.getInt(i) + 1;
                i++;
            }
        }
        cursor.close();
        // database_close();
        return count;
    }
    public static void updateCount(int id)
    {
        database_open();
        int value=getCounter(id);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbSchema.Table_Spinner.COL_COUNTER,value);
        contentValues.put(DbSchema.Table_Spinner.COL_USED,"1");
        int myUpdate =  database.update(DbSchema.Table_Spinner.TABLE_NAME,contentValues,"id="+id,null);

        if(myUpdate>0)
            Log.d(TAG,"this shit worked");
        else Log.d(TAG,"this shit doesn't work");

        database_close();

    }

    public static int maxId(){
        database_open();
        String query = "SELECT MAX("+DbSchema.Table_Spinner.COL_ID+") FROM "+DbSchema.Table_Spinner.TABLE_NAME;
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

