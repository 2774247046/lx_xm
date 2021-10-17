package com.example.lx_xm.UI;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lx_xm.Data.Sqlite_data;
import com.example.lx_xm.R;

import java.util.Calendar;

public class UP_ye extends AppCompatActivity implements View.OnClickListener{
    private EditText title_UP;
    private EditText content_UP;
    private Button Up_title;
    private Button button_qd;
    private Intent intent;
    private Sqlite_data data=new Sqlite_data(UP_ye.this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.up_layout);
        title_UP=findViewById(R.id.title_UP);
        content_UP=findViewById(R.id.content_UP);
        Up_title=findViewById(R.id.Up_button);
        button_qd=findViewById(R.id.button_qd);
        button_qd.setOnClickListener(this);
        EditText_context();
        Up_title.setOnClickListener(this);
        Button button_no=findViewById(R.id.No_btn);
        button_no.setOnClickListener(this);
    }
    private void EditText_context(){
        intent=getIntent();
        String id=intent.getStringExtra("_id");
        String title=intent.getStringExtra("title");
        String time=intent.getStringExtra("time");
        String content=intent.getStringExtra("content");
        title_UP.setText(title);
        content_UP.setText(content);
        Up_title.setText(time);
    }
    @Override
    public void onClick(View v) {
        Button button=(Button)v;
        switch (button.getId()){
            case R.id.Up_button:
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog datePickerDialog=new DatePickerDialog(UP_ye.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Up_title.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.button_qd:
                String id=intent.getStringExtra("_id");
                String title_text=title_UP.getText().toString();
                String a=Up_title.getText().toString();
                String content_text=content_UP.getText().toString();
                String []text={title_text,a,content_text};
                sql_Up(id,text);
                break;
            case R.id.No_btn:
                this.finish();
        }
    }
    private void sql_Up(String id,String[]Up_text){
        int a=data.UP(id,Up_text);
        if (a==1){
            Toast.makeText(UP_ye.this,"修改成功",Toast.LENGTH_LONG).show();
        }else if (a==0){
            Toast.makeText(UP_ye.this,"修改失败",Toast.LENGTH_LONG).show();
        }
    }
}
