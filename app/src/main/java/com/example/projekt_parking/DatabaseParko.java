package com.example.projekt_parking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;

public class DatabaseParko extends SQLiteOpenHelper {
    public static String DATABASE_NAME="parko.DB";
    public static String TABLE_NAME="parko_table";
    public static String COL1="ID";
    public static String COL2="Targa";
    public static String COL3="Zona";
    public static String COL4="Orari";
    public static String COL5="Data";
    public static String COL6="Koordinatat_Gjeresia";
    public static String COL7="Koordinatat_Gjatesia";



    public DatabaseParko(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Targa TEXT,Zona TEXT,Orari INTEGER,Data TEXT,Koordinatat_Gjeresia TEXT,Koordinatat_Gjatesia TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean bookParkingSpot(String Targa ,String Zona,String Orari,String Data,String Koordinatat_Gjeresia,String Koordinatat_Gjatesia){
        SQLiteDatabase db_A=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,Targa);
        contentValues.put(COL3,Zona);
        contentValues.put(COL4,Orari);
        contentValues.put(COL5,Data);
        contentValues.put(COL6,Koordinatat_Gjeresia);
        contentValues.put(COL7,Koordinatat_Gjatesia);


        long result= db_A.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }
    public Cursor viewAll(){
        SQLiteDatabase db_A= this.getWritableDatabase();
        Cursor result= db_A.rawQuery("select * from "+TABLE_NAME,null);
        return result;
    }
}
