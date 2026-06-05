package com.rtechnologies.samar.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.rtechnologies.samar.Samar;
import com.rtechnologies.samar.constant.SharedPrefKeys;

public class SharedPref {
    private static final SharedPref instance=new SharedPref();
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    private  SharedPref(){
        this.preferences= Samar.getInstance().getSharedPreferences(SharedPrefKeys.SAMAR.toString(), Context.MODE_PRIVATE);
        this.editor=preferences.edit();
    }
    public static SharedPref getInstance(){
        return instance;
    }
    public void putString(String key,String value){
        editor.putString(key,value);
        editor.apply();
    }
    public String getString(String key){
        return preferences.getString(key,null);
    }
    public void putInt(String key,int value){
        editor.putInt(key,value);
        editor.apply();
    }
    public int getInt(String key){
        return preferences.getInt(key,0);
    }
    public void putBoolean(String key,boolean value){
        editor.putBoolean(key,value);
        editor.apply();
    }
    public boolean getBoolean(String key){
        return preferences.getBoolean(key,false);
    }
    public void putLong(String key,long value){
        editor.putLong(key,value);
        editor.apply();
    }
    public long getLong(String key){
        return preferences.getLong(key,0);
    }
    public void putFloat(String key,float value){
        editor.putFloat(key,value);
        editor.apply();
    }
    public float getFloat(String key){
        return preferences.getFloat(key,0);
    }



}
