package com.example.womansafety;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="Contact.db";
    public static final String TABLE_NAME="contact_data";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="NUMBER";
    public DatabaseHelper(Context context) {
        super(context,DB_NAME,null,1);
        SQLiteDatabase db=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT,NUMBER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }
    public boolean insertData(String name,String number)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(COL_2,name);
        c.put(COL_3,number);
        long result =db.insert(TABLE_NAME,null,c);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT ID,NAME,NUMBER FROM "+TABLE_NAME ,null);
        return res;
    }
    public Cursor getNumber()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT NUMBER FROM "+TABLE_NAME,null);
        return res;

    }
    public Integer delete(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

}
