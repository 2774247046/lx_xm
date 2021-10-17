package com.example.lx_xm.Sqlite;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Frontpage_sqlite extends SQLiteOpenHelper {
    private String Sql_table="create table MemoDB(" +
            "_id integer primary key AUTOINCREMENT," +
            "title Varchar(40)," +
            "time Varchar(18)," +
            "content varchar(500))";
    public Frontpage_sqlite(@Nullable Context context) {
        super(context,"Frontpage.db", null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Sql_table);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists MemoDB ");
        onCreate(db);
    }
    public Cursor query(String sql,String[]args){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,args);
        return cursor;
    }
    public void delSql(String sql){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }
    public void Up(String sql,String []age){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql,age);
    }
    public void Add(String sql,String[] age){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql,age);
    }
}
