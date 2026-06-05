package com.rtechnologies.samar;

import android.app.Application;
import android.content.Context;

import com.rtechnologies.samar.core.Core;

public class Samar extends Application {
    private static Samar instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        Core.init(getGlobalContext());
    }

    public static Samar getInstance(){
        return instance;
    }
    public static Context getGlobalContext(){
        return instance.getApplicationContext();
    }
}
