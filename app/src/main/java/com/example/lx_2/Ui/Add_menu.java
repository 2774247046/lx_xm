package com.example.lx_2.Ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lx_2.adapter.contactor;
import com.example.lx_xm.R;

import java.util.Calendar;

public class Add_menu extends AppCompatActivity {
    private Button menu_add_btn;
    private EditText editText_name,editText_alias,editText_Phone,editText_home_phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_add_layout);
        menu_add_btn=findViewById(R.id.menu_add_btn);
        editText_name=findViewById(R.id.text_name);
        editText_alias=findViewById(R.id.text_alias);
        editText_Phone=findViewById(R.id.Phone);
        editText_home_phone=findViewById(R.id.home_Phone);
        menu_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String name=editText_name.getText().toString();
              String alias=editText_alias.getText().toString();
              String Phone=editText_Phone.getText().toString();
              String home_phone=editText_home_phone.getText().toString();
                contactor contactor=new contactor();
                contactor.setName(name);
                contactor.setRemark(alias);
                contactor.setWork_phone(Phone);
                contactor.setHome_phone(home_phone);
                contactor.save();
                Toast.makeText(Add_menu.this,"添加成功",Toast.LENGTH_LONG).show();
            }
        });
    }
}
