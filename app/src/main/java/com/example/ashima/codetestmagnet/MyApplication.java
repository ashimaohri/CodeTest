package com.example.ashima.codetestmagnet;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ashima on 10/8/2015.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;

    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {

        return sInstance.getApplicationContext();
    }
}
