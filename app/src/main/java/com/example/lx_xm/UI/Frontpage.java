package com.example.lx_xm.UI;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.lx_xm.Adapter.Frontpage_Adapter;

import com.example.lx_xm.Data.FrontPage_data;
import com.example.lx_xm.Data.Sqlite_data;
import com.example.lx_xm.List.Frontpage_list;
import com.example.lx_xm.R;

import java.util.ArrayList;
import java.util.List;

public class Frontpage extends AppCompatActivity implements View.OnClickListener {
    private List<Frontpage_list> list=new ArrayList<>();
    private Sqlite_data data=new Sqlite_data(Frontpage.this);
    private Frontpage_Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context=Frontpage.this;
    private ListView listView;
    private Button add_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar=getSupportActionBar();
        if (bar!=null){
            bar.hide();
        }
        setContentView(R.layout.frontpage_main_layout);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(this);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
//        add();
        Adapter_context();
        listView=findViewById(R.id.frontpage_View);
        adapter=new Frontpage_Adapter(list,R.layout.adapter_layout,Frontpage.this);
        listView.setAdapter(adapter);
    }
    private void Adapter_context(){
        list=new FrontPage_data().Adapter_context(context);
    }
    private void refreshFruits(){
        Adapter_context();
        adapter=new Frontpage_Adapter(list,R.layout.adapter_layout,Frontpage.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(Frontpage.this,"刷新成功",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onClick(View v) {
        Button button=(Button)v;
        switch (button.getId()){
            case R.id.add_button:
                Intent intent=new Intent(Frontpage.this, com.example.lx_xm.UI.Add_ye.class);
                startActivity(intent);
        }
    }
}
