package com.example.lx_2.adapter;

import android.app.Activity;
import android.app.Application;

import org.litepal.LitePal;

public class Latepal_name extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
