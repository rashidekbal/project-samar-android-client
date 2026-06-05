package com.rtechnologies.samar.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidnetworking.AndroidNetworking;
import com.rtechnologies.samar.constant.SharedPrefKeys;

public class Core {

    public static void init(Context context){
        AndroidNetworking.initialize(context);
        SharedPreferences preferences=context.getSharedPreferences(SharedPrefKeys.SAMAR.toString(),Context.MODE_PRIVATE);
    }
}
