package com.lx3;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lx_xm.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class web extends AppCompatActivity {
    private EditText user_name,user_password;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);
        user_name=findViewById(R.id.user_name);
        user_password=findViewById(R.id.user_password);
        button=findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_admin=user_name.getText().toString();
                getLogin(user_admin,web.this);
            }
        });
    }
        private void getLogin(String userName, Context context){
                HTTPConnection(userName, new HttpURlConnection() {
                    @Override
                    public void Finish(String a) {
                        try {
                            JSONObject jsonObject=new JSONObject(a);
                            String Json=jsonObject.getString("UserName");
                            Log.d("Json",Json);
                            if (Json.equals("yes")){
                                runOnUiThread(() ->{
                                    Toast.makeText(context, "验证成功", Toast.LENGTH_SHORT).show();
                                });
                            }else{
                                runOnUiThread(()->{
                                    Toast.makeText(context, "验证失败", Toast.LENGTH_SHORT).show();
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void End(Exception e) {
                        e.printStackTrace();
                    }
                });
            }
    private void HTTPConnection(String userName,HttpURlConnection uRlConnection) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
//                    OkHttpClient client=new OkHttpClient();
//                    Request request=new Request.Builder()
//                            .url("http://10.0.2.2:8080/demo1_war_exploded/hello-servlet?type=login&UserName="+userName)
//                            .build();
//                    Response response=client.newCall(request).execute();
//                    String uu=response.body().string();
//                    Log.d("uu",uu);
                    URL url=new URL("http://10.0.2.2:8080/demo1_war_exploded/hello-servlet?type=login&UserName="+userName);
                    connection= (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    connection.setRequestMethod("GET");
                    InputStream stream=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
                    StringBuilder stringBuilder=new StringBuilder();
                    String a;
                    while ((a=reader.readLine())!=null){
                        stringBuilder.append(a);
                    }
                    if (uRlConnection!=null){
                        uRlConnection.Finish(stringBuilder.toString());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if (uRlConnection!=null){
                        uRlConnection.End(e);
                    }
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
