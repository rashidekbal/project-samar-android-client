package com.rtechnologies.samar.utils;

import com.google.gson.Gson;

public class JsonUtil<T> {
    public String getJsonString(T object){
        Gson gson=new Gson();
        return gson.toJson(object);
    }
}
