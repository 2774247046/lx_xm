package com.example.xm3;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.lx_xm.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class Borrow_book extends AppCompatActivity {
    private ListView listView;
    private List<Boorrow_list>addlist=new ArrayList<>();
    private  adapter a;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_book_layout);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        JSon();
        listView=findViewById(R.id.list_book);
        a=new adapter(addlist,Borrow_book.this);
        listView.setAdapter(a);
        SwipeRefreshLayout swipeRefreshLayout=findViewById(R.id.list_Up);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        a.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
    }
    class adapter extends BaseAdapter{
        private List<Boorrow_list> list;
        private Context context;
        public adapter(List<Boorrow_list> list, Context context) {
            this.list = list;
            this.context = context;
        }
        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public Object getItem(int position) {
            return list.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HH hh;
            if (convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.xm3_book_text,parent,false);
                hh=new HH();
                hh.bookName_text=convertView.findViewById(R.id.bookName);
                hh.bookImage_text=convertView.findViewById(R.id.bookImage);
                hh.bookInfo_text=convertView.findViewById(R.id.bookInfo);
                hh.Borrow_btn=convertView.findViewById(R.id.book_borrow_btn);
                hh.Borrow_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance();
                        DatePickerDialog a=new DatePickerDialog(Borrow_book.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Toast.makeText(Borrow_book.this, year+"-"+month+"-"+dayOfMonth, Toast.LENGTH_SHORT).show();
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                        a.show();
                    }
                });
                convertView.setTag(hh);
            }else{
                hh=(HH) convertView.getTag();
            }
            Boorrow_list boorrowList=list.get(position);
            hh.bookInfo_text.setText(boorrowList.getBookInfo());
            hh.bookImage_text.setText(boorrowList.getBookImage());
            hh.bookName_text.setText(boorrowList.getBookName());
            return convertView;
        }
    }
    class HH{
        TextView bookInfo_text;
        TextView bookImage_text;
        TextView bookName_text;
        Button Borrow_btn;
    }
    private void JSon(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("http://10.0.2.2:8080/MyBook?type=AllData")
                            .build();
                    Response response=client.newCall(request).execute();
                    String text=response.body().string();
                    borrow_inti(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void borrow_inti(String text){
        try {
//            JSONArray array=new JSONArray(text2);
//            Log.d("JSON", String.valueOf(array));
            JSONObject jsonObject=new JSONObject(text);
            JSONArray array=jsonObject.getJSONArray("data");
            for (int i=0;i<array.length();i++) {
                JSONObject j = array.getJSONObject(i);
                String bookInfo = j.optString("bookInfo");
                String bookimage = j.optString("bookImage");
                String bookname = j.optString("bookName");
                Boorrow_list boorrowList = new Boorrow_list(bookInfo, bookimage, bookname);
                addlist.add(boorrowList);
            }
            a.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

