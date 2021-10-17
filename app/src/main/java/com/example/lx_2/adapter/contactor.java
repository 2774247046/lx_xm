package com.example.lx_2.adapter;

import org.litepal.crud.LitePalSupport;

import java.util.UUID;

public class contactor extends LitePalSupport {
    private int  _id;
    private String name;
    private String remark;
    private String Home_phone;
    private String Work_phone;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHome_phone() {
        return Home_phone;
    }

    public void setHome_phone(String home_phone) {
        Home_phone = home_phone;
    }

    public String getWork_phone() {
        return Work_phone;
    }

    public void setWork_phone(String work_phone) {
        Work_phone = work_phone;
    }
}
