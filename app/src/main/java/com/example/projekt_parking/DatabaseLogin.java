package com.example.projekt_parking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseLogin extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Login.DB";
    public static final String TABLE_NAME = "login_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "Username";
    public static final String COL3 = "Password";




    public DatabaseLogin(Context context){
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Username TEXT,Password TEXT)");

    };

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean register(String Username,String Password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,Username);
        contentValues.put(COL3,Password);

        long result= db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }
    public boolean checkUser(String Username,String Password){
        String[] columns= {COL1};
        SQLiteDatabase db=getReadableDatabase();
        String selection= COL2+"=?"+" and "+COL3+"=?";
        String[] selectionArgs={Username,Password};
        Cursor cursor=db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count= cursor.getCount();
        cursor.close();
        db.close();
        if (count>0)
            return true;
        else
            return  false;
    }
}
