package com.example.shakirislam.databaseappattempt2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "DatabaseHelper.db";
    private static final String TABLE_NAME = "Example";
    private static final String COL1 = "ID";
    private static final String COL2 = "Input";


    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, value);
        Log.d(TAG, "addData: Adding" + value + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME,null, contentValues);

        if (result == -1){
            return false;
            //-1 value will get returned, therefore this is used to check whether the value was inserted correctly or not

        }else{
            return true;
        }
    }


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean resetTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String resetQuery = "DELETE FROM " + TABLE_NAME;
        db.execSQL(resetQuery);
        return true;

    }



























}
