package com.example.sitm3033.mycontactapp2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact2018.db";
    public static final String TABLE_NAME = "Contact2018_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_ADDRESS_CONTACT = "contact";
    public static final String COLUMN_NUMBER_CONTACT = "contact";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTERGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME_CONTACT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
        Log.d("MyContactApp", "Databasehelper: contructing Databasehelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp", "Databasehelper: creating Databasehelper");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp", "Databasehelper: upgrading Databasehelper");
        db.execSQL(SQL_CREATE_ENTRIES);
        onCreate(db);
    }


    public boolean insertData(String name) {
        Log.d("MyContactApp", "Databasehelper: inserting Databasehelper");
        SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues contentValue = new android.content.ContentValues();
        contentValue.put(COLUMN_NAME_CONTACT, name);
        contentValue.put(COLUMN_ADDRESS_CONTACT, address);
        contentValue.put(COLUMN_NUMBER_CONTACT, number);
        long result = db.insert(TABLE_NAME, null , contentValue);
        if (result == -1){
            Log.d("MyContactApp", "Databasehelper: Contact insert - FAILED");
            return false;
        }
        else{
            Log.d("MyContactApp", "Databasehelper: Contact insert - FAILED");
            return true;
        }
    }

    public Cursor getAllData(){
        Log.d("MyContactApp", "Databasehelper: calling getAllData method");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}