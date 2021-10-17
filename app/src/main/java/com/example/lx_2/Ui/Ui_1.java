package com.example.lx_2.Ui;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.lx_2.adapter.RecyclerView_adapter;
import com.example.lx_2.adapter.contactor;
import com.example.lx_xm.R;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ui_1 extends AppCompatActivity{
    private RecyclerView recyclerView;
    private List<contactor> list=new ArrayList<>();
    private RecyclerView_adapter recyclerView_adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_1_layout);
        swipeRefreshLayout=findViewById(R.id.Ui_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerViewUP();
            }
        });
//        add();
        list_ADD();
        recyclerView=findViewById(R.id.ui_View);
        recyclerView_adapter=new RecyclerView_adapter(list,this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyclerView_adapter);
    }
    private void  add(){
        LitePal.getDatabase();
        contactor context=new contactor();
        context.setName("李逵");
        context.setRemark("黑旋风");
        context.setWork_phone("8677321");
        context.setHome_phone("8677321");
        context.save();
        list.add(context);
    }
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.add_ye_menu,menu);
        return super.onCreatePanelMenu(featureId, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_menu:
                Intent intent=new Intent(Ui_1.this,Add_menu.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
    private void list_ADD(){
        list=LitePal.select("_id","name","remark","Work_phone","Home_phone").find(contactor.class);
    }
    private void recyclerViewUP(){
            list_ADD();
            Toast.makeText(this, "加载成功", Toast.LENGTH_SHORT).show();
            recyclerView_adapter=new RecyclerView_adapter(list,this);
            recyclerView.setAdapter(recyclerView_adapter);
            recyclerView_adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
    }
}
