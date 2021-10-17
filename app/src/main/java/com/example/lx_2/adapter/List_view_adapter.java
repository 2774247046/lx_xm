package com.example.lx_2.adapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lx_2.Ui.UP_Ui;
import com.example.lx_xm.R;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
public class List_view_adapter extends BaseAdapter implements View.OnClickListener {
    private List<List_2> a=new ArrayList<List_2>();
    private int _id;
    private Context context;
    private String name, remark, Work_phone, Home_phone;

    public List_view_adapter(List<List_2> a, int _id, Context context, String name, String remark, String work_phone, String home_phone) {
        this.a = a;
        this._id = _id;
        this.context = context;
        this.name = name;
        this.remark = remark;
        Work_phone = work_phone;
        Home_phone = home_phone;
    }

    @Override
    public int getCount() {
        return a.size();
    }

    @Override
    public Object getItem(int position) {
        return a.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Text text;
        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_menu_layout_text,null,false);
            text=new Text();
            text.textView=convertView.findViewById(R.id.text_xz);
            text.textView2=convertView.findViewById(R.id.text_xg);
            text.textView3=convertView.findViewById(R.id.text_sc);
            text.textView4=convertView.findViewById(R.id.text_ck);
            text.textView2.setOnClickListener(this);
            text.textView3.setOnClickListener(this);
            text.textView4.setOnClickListener(this);
            convertView.setTag(text);
        }else {
            text=(Text) convertView.getTag();
        }
        List_2 list_2=a.get(position);
        text.textView.setText(list_2.getText());
        text.textView2.setText(list_2.getText2());
        text.textView3.setText(list_2.getText3());
        text.textView4.setText(list_2.getText4());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_sc:
                new AlertDialog.Builder(context)
                        .setTitle("确定要删除吗？")
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int a=LitePal.deleteAll(contactor.class, "id = ?", String.valueOf(_id));
                                if (a==1){
                                    Toast.makeText(context,"删除成功",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(context,"删除失败",Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                break;
            case R.id.text_xg:
                Intent intent=new Intent(context, UP_Ui.class);
                String id= String.valueOf(_id);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("remark",remark);
                intent.putExtra("Work_phone",Work_phone);
                intent.putExtra("Home_phone",Home_phone);
                context.startActivities(new Intent[]{intent});
        }
    }
    class Text{
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
    }
}
