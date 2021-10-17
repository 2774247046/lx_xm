package com.example.lx_xm.Data;

import android.content.Context;
import android.database.Cursor;

import com.example.lx_xm.List.Frontpage_list;

import java.util.ArrayList;
import java.util.List;

public class FrontPage_data {
    public List<Frontpage_list> Adapter_context(Context context){
        Sqlite_data data=new Sqlite_data(context);
        List<Frontpage_list>list=new ArrayList<>();
        Frontpage_list frontpageList;
        Cursor cursor=data.findAll();
        if (cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    frontpageList=new Frontpage_list(
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("content")));
                    list.add(frontpageList);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        return list;
    }
}
