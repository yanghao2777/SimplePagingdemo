package com.qascg.pagingapp;

import android.app.Application;
import android.content.Context;

public class PagingApp  extends Application {
    private static Context application;

    public static Context getAppContext(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = getApplicationContext();
    }
}
