package com.example.lx_xm.UI;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lx_xm.Data.Sqlite_data;
import com.example.lx_xm.R;

import java.util.Calendar;

public class Add_ye extends AppCompatActivity {
    private Button add_button,add__time;
    private EditText title;
    private EditText context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);
        add_button=findViewById(R.id.btn_add);
        title=findViewById(R.id.add_title);
        add__time=findViewById(R.id.add_time);
        context=findViewById(R.id.add_content);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_text=title.getText().toString();
                String context_text=context.getText().toString();
                String time_add=add__time.getText().toString();
                String []age={title_text,time_add,context_text};
                Sqlite_data data=new Sqlite_data(Add_ye.this);
                data.add(age,Add_ye.this);
            }
        });
        add__time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDlg();
            }
        });
    }
    private void showDatePickDlg () {
       Calendar calendar=Calendar.getInstance();
       DatePickerDialog datePickerDialog=new DatePickerDialog(Add_ye.this, new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               add__time.setText(year+"-"+month+"-"+dayOfMonth);
           }
       },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
       datePickerDialog.show();
    }
}
