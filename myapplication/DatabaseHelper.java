package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Tenant.db";
    public static final String TABLE_NAME = "tenant_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PHONE_NUMBER";
    public static final String COL_4 = "DATE";
    public static final String COL_5 = "ROOM";
    public static final String COL_6 = "PREVIOUS_UNIT";
    public static final String COL_7 = "CURRENT_UNIT";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PHONE_NUMBER TEXT,DATE TEXT,ROOM TEXT,PREVIOUS_UNIT TEXT,CURRENT_UNIT TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String phNo,String date,String room,String prevUnit,String cUnit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,phNo);
        contentValues.put(COL_4,date);
        contentValues.put(COL_5,room);
        contentValues.put(COL_6,prevUnit);
        contentValues.put(COL_7,cUnit);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM "+ TABLE_NAME,null);
        return res;
    }

    public boolean updateDatabase(String id, String name, String phNo, String date, String room, String prevUnit, String cUnit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1,id);
        values.put(COL_2,name);
        values.put(COL_3,phNo);
        values.put(COL_4,date);
        values.put(COL_5,room);
        values.put(COL_6,prevUnit);
        values.put(COL_7,cUnit);

        db.update(TABLE_NAME,values," ID = ? ",new String[] { id });
        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME," ID = ? ",new String[] { id });

    }

}
