package com.example.lx_xm.List;

public class Frontpage_list{
    private String name;
    private String sj;
    private String id;
    private String context;

    public Frontpage_list(String name, String sj, String id,String context) {
        this.name = name;
        this.sj = sj;
        this.id = id;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }
}
