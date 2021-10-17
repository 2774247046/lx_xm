package com.example.lx_2.adapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lx_xm.R;


import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class RecyclerView_adapter extends RecyclerView.Adapter<RecyclerView_adapter.ViewHolder> {
    private List<contactor>list;
    private Context context;
    public RecyclerView_adapter(List<contactor> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ui_text_image_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=viewHolder.getBindingAdapterPosition();
                int id=list.get(a).get_id();
                String name=list.get(a).getName();
                String remark=list.get(a).getRemark();
                String Work_phone=list.get(a).getWork_phone();
                String Home_phone=list.get(a).getHome_phone();
                Log.d("全部",name+remark+Work_phone+Home_phone);

                ShowDialog(id,name,remark,Work_phone,Home_phone);
            }
        });
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id= String.valueOf(list.get(position).get_id());
        holder.textView_text_id.setText(id);
        holder.text_name.setText(list.get(position).getName());
        holder.text_phone.setText(list.get(position).getHome_phone());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_text_id;
        TextView text_name;
        TextView text_phone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_text_id=itemView.findViewById(R.id.text_id);
            text_name=itemView.findViewById(R.id.text_name);
            text_phone=itemView.findViewById(R.id.text_Work_phone);
        }
    }
//    把menu添加到控键里
//    private void openOptionsMenu(View v){
//        PopupMenu popupMenu=new PopupMenu(context,v, Gravity.CENTER);
//        popupMenu.getMenuInflater().inflate(R.menu.up_delete_menu,popupMenu.getMenu());
//        popupMenu.show();
//    }
//    把listview添加到警告框里去
    public void ShowDialog(int _id,String name,String remark,String Work_phone,String Home_phone) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        View view=LayoutInflater.from(context).inflate(R.layout.view_menu_layout,null,false);
        ListView listView=view.findViewById(R.id.List_view_list);
        String []a={"选择","修改","删除","查看"};
        List<List_2>list=new ArrayList<List_2>();
        List_2 list_2=new List_2(a[0],a[1],a[2],a[3]);
        list.add(list_2);
        List_view_adapter l=new List_view_adapter(list,_id,context,name,remark,Work_phone,Home_phone);
        listView.setAdapter(l);
        dialog.setView(view).show();
    }
}
