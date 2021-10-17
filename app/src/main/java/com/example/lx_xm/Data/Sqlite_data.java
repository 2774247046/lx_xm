package com.example.lx_xm.Data;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.example.lx_xm.Sqlite.Frontpage_sqlite;
public class Sqlite_data  {
    private Frontpage_sqlite sqlite;
    public Sqlite_data(Context context) {
        this.sqlite = new Frontpage_sqlite(context);
    }
//    添加数据
    public void add(String[]args,Context context){
        String sql="insert into MemoDB(title,time,content)values(?,?,?)";
        sqlite.Add(sql,args);
        Toast.makeText(context,"成功",Toast.LENGTH_SHORT).show();
    }
    public Cursor findAll(){
        String sql="select * from MemoDB";
        return sqlite.query(sql,null);
    }
    public void Delete(String id){
        String sql="delete from MemoDB where _id = "+id;
        sqlite.delSql(sql);
    }
    public int  UP(String _id,String []age){
        int a ;
        if (age!=null){
            String sql="update MemoDB set title= ?,time= ?,content= ? where _id="+_id;
            sqlite.Up(sql,age);
            a=1;
        }else{
            a=0;
        }
        return a;
    }
}
