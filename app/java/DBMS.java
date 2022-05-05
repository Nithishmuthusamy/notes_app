package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBMS extends SQLiteOpenHelper {
    private static final String NAME = "NAME";
    private static final String Id = "Id";
    private static final String names = "names";
    private static final String NOTES = "NOTES";
    public DBMS(@Nullable Context context) {
        super(context, "sample", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " + names + " ( " + Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " STRING,"+ NOTES +" STRING)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+names);
        onCreate(sqLiteDatabase);
    }

    public boolean addon(String a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, a);
        long num = db.insert(names, null, cv);
        if (num == -1)
            return false;
        else
            return true;
    }
    public boolean addnotes(String a,String b) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, a);
        long num = db.update(names,cv,"NAME = ?",new String[] { b });
        if (num == -1)
            return false;
        else
            return true;
    }
    public boolean delete(String a) {
        SQLiteDatabase db=this.getWritableDatabase();
        String Querytable=" DELETE FROM "+names+" WHERE "+NAME+ " LIKE " + "'" + a + "'";
        Cursor cursor=db.rawQuery(Querytable,null);
        //System.out.println(cursor.toString());
        if(cursor.moveToFirst())
            return true;
        else
            return false;
    }

    public ArrayList<String> getEveryThing() {
    ArrayList<String> table=new ArrayList<>();
    SQLiteDatabase db=this.getReadableDatabase();
    String Querytable=" SELECT * FROM "+names;
    Cursor cursor=db.rawQuery(Querytable,null);
    if(cursor.moveToFirst())
    {
        do{
            table.add(cursor.getString(1));
            System.out.println(cursor.getString(1));
            System.out.println("there is no thing");
        }
        while(cursor.moveToNext());
    }
    else
    {
        System.out.println("there is no thing");
    };
    cursor.close();
    db.close();
    return table;
    }

}
