package com.example.lx_2.Ui;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lx_2.adapter.contactor;
import com.example.lx_xm.R;

import org.litepal.LitePal;

import java.util.List;
public class UP_Ui extends AppCompatActivity {
    private EditText name,remark,Work_phone,Home_phone;
    private Button button;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.up2_layout);
        name=findViewById(R.id.up_name);
        remark=findViewById(R.id.up_remark);
        Work_phone=findViewById(R.id.up_Work_phone);
        Home_phone=findViewById(R.id.up_Home_phone);
        button=findViewById(R.id.baoCui_btn);
        inti();
        button.setOnClickListener(v -> {
            String id=intent.getStringExtra("id");
            String get_name=name.getText().toString();
            String get_remark=remark.getText().toString();
            String get_Work_phone=Work_phone.getText().toString();
            String get_Home_phone=Home_phone.getText().toString();
            contactor r=new contactor();
            r.setName(get_name);
            r.setRemark(get_remark);
            r.setWork_phone(get_Work_phone);
            r.setHome_phone(get_Home_phone);
            int a=r.updateAll("id=?",id);
            if (a==1){
                Toast.makeText(UP_Ui.this,"修改成功",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(UP_Ui.this,"修改失败",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void inti(){
        intent=getIntent();
        String name_s=intent.getStringExtra("name");
        String remark_=intent.getStringExtra("remark");
        String Work_phone_=intent.getStringExtra("Work_phone");
        String Home_phone_=intent.getStringExtra("Home_phone");
        name.setText(name_s);
        remark.setText(remark_);
        Work_phone.setText(Work_phone_);
        Home_phone.setText(Home_phone_);
    }
}
