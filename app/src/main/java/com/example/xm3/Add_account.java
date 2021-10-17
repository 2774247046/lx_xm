package com.example.xm3;
import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lx_xm.R;

import java.io.IOException;
import java.security.Permission;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
public class Add_account extends AppCompatActivity {
    private EditText Edit_nam,Edit_password;
    private Button Btn_account;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_account_layout);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        Edit_nam=findViewById(R.id.edit_name);
        Edit_password=findViewById(R.id.edit_password);
        Btn_account=findViewById(R.id.btn_account);
        Btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=Edit_nam.getText().toString();
                String password=Edit_password.getText().toString();
                aa(name,password);
            }
        });
    }
    private void aa(String name,String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    RequestBody body=new FormBody.Builder()
                            .add("userName",name)
                            .add("userPass",password)
                            .build();
                    Request request=new Request.Builder()
                            .url("http://10.0.2.2:8080/UserServlet?type=addUser&userName=+"+name+"&userPass="+password)
                            .post(body)
                            .build();
                    Response response=client.newCall(request).execute();
                    String v=response.body().string();
                    verify(v);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void verify(String verify){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (verify.equals("{\"message\":\"yes\"}")){
                    Toast.makeText(Add_account.this, "注册成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Add_account.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
