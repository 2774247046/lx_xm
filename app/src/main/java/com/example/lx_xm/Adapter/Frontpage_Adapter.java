package com.example.lx_xm.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lx_xm.Data.Sqlite_data;
import com.example.lx_xm.List.Frontpage_list;
import com.example.lx_xm.R;
import com.example.lx_xm.UI.UP_ye;

import java.util.List;

public class Frontpage_Adapter extends BaseAdapter {
    private List<Frontpage_list>list;
    private int layout;
    private Context context;
    private Frontpage_list frontpageList;
    public Frontpage_Adapter(List<Frontpage_list> list, int layout, Context context) {
        this.list = list;
        this.layout = layout;
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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(layout,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView=convertView.findViewById(R.id.adapter_text);
            viewHolder.text_sj=convertView.findViewById(R.id.adapter_text_sj);
            viewHolder.text_id=convertView.findViewById(R.id.adapter_text_id);
//            viewHolder.text_context=convertView.findViewById(R.id.text_context);
            viewHolder.UP=convertView.findViewById(R.id.UP_btn);
            viewHolder.delete=convertView.findViewById(R.id.delete_btn);
            convertView.setTag(viewHolder);
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setTitle("系统通知")
                            .setMessage("是否删除该设备的备忘录")
                            .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Sqlite_data data=new Sqlite_data(parent.getContext());
                                    data.Delete(list.get(position).getId());
                                    notifyDataSetChanged();
                                    Log.d("id",list.get(position).getId());
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create().show();
                }
            });
            viewHolder.UP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id=list.get(position).getId();
                    String tittle=list.get(position).getName();
                    String time=list.get(position).getSj();
                    String Context=list.get(position).getContext();
                    Log.d("context",Context);
                    xuGai(context,id,tittle,time,Context);
                }
            });
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        frontpageList=list.get(position);
        viewHolder.textView.setText(frontpageList.getName());
        viewHolder.text_sj.setText(frontpageList.getSj());
        viewHolder.text_id.setText(frontpageList.getId());
//        viewHolder.text_context.setText(frontpageList.getContext());
        return convertView;
    }
    class  ViewHolder{
        TextView text_id;
        TextView textView;
        TextView text_sj;
        TextView text_context;
        Button UP;
        Button delete;
    }
    private void Context(ViewHolder view, int a){
//        Frontpage_list frontpageList=list.get(a);
//        SQLiteDatabase helperData=sqlite.getWritableDatabase();
//        Cursor cursor=helperData.query("MemoDB",null,null,null,null,null,null);
//        frontpageList.set
//        SQLiteDatabase helper=sqlite.getWritableDatabase();
//        Cursor cursor=helper.query("MemoDB",null,null,null,null,null,null);
//        Frontpage_list frontpageList=list.get(a);
//        view.textView.setText(frontpageList.getName());
//        view.text_sj.setText(frontpageList.getSj());
//        view.text_id.setText(frontpageList.getId());
    }

//    private void initPopWindow(View v,ViewGroup parent,int position){
//        View view=LayoutInflater.from(context).inflate(R.layout.popupwindow_layout,null,false);
//        Button de_btn=view.findViewById(R.id.delete_btn);
//        Button up_btn=view.findViewById(R.id.Up_btn);
//        PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setTouchable(true);
//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//            }
//        });
//        popupWindow.setBackgroundDrawable(new ColorDrawable(1*000000));
//        popupWindow.showAsDropDown(v, 1100, 0);
//        de_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(context)
//                        .setTitle("系统通知")
//                        .setMessage("是否删除该设备的备忘录")
//                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Sqlite_data data=new Sqlite_data(parent.getContext());
//                                data.Delete(list.get(position).getId());
//                                notifyDataSetChanged();
//                                Log.d("id",list.get(position).getId());
//                            }
//                        })
//                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        }).create()
//                        .show();
//            }
//        });
//        up_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }

    private void xuGai(Context context,String id,String title,String time,String content){
        Intent intent=new Intent(context, UP_ye.class);
        intent.putExtra("_id",id);
        intent.putExtra("title",title);
        intent.putExtra("time",time);
        intent.putExtra("content",content);
        context.startActivities(new Intent[]{intent});
    }
}
