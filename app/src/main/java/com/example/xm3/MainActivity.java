package com.example.xm3;

import android.content.Intent;
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

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button button_login;
    private EditText text_admin,text_password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar a=getSupportActionBar();
        if (a!=null){
            a.hide();
        }
        setContentView(R.layout.xm3_latouy);
        TextView textView=findViewById(R.id.add_account);
        button_login=findViewById(R.id.login_btn);
        text_admin=findViewById(R.id.login_admin);
        text_password=findViewById(R.id.login_password);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String admin=text_admin.getText().toString();
                String password=text_password.getText().toString();
                o(admin,password);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Add_account.class);
                startActivity(intent);
            }
        });
    }
    private void o(String admin,String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                RequestBody body=new FormBody.Builder()
                        .add("userName",admin)
                        .add("userPass",password)
                        .build();
                Request request=new Request.Builder()
                        .url("http://10.0.2.2:8080/UserServlet?type=login")
                        .post(body)
                        .build();
                try {
                    Response response=client.newCall(request).execute();
                    String text=response.body().string();
                    uiThread(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void uiThread(String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!text.equals("{\"message\":\"no\"}")){
                    Intent intent=new Intent(MainActivity.this,Borrow_book.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
