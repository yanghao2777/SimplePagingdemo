package com.qascg.pagingapp;

import androidx.annotation.Keep;

@Keep
public class MainDataBean {
    public String name;
    public long addData;

    public MainDataBean(String name,long addData) {
        this.name = name;
        this.addData = addData;
    }
}
