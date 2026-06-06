package com.rtechnologies.samar.networking.abstractClasses;

import androidx.annotation.Nullable;

import com.rtechnologies.samar.interfaces.ApiResponseInterface;

import org.json.JSONObject;

public abstract class Networking {
    public abstract void post(@Nullable String TAG,String url, @Nullable JSONObject body, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface);
    public abstract void get(@Nullable String TAG,String url, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface);
    public abstract void patch(@Nullable String TAG,String url, @Nullable JSONObject body, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface);
    public abstract void delete(@Nullable String TAG,String url, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface);

    public abstract void cancelRequest(String Tag);


}
