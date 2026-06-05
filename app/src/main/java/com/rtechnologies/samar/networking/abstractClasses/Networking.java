package com.rtechnologies.samar.networking.abstractClasses;

import androidx.annotation.Nullable;

import com.rtechnologies.samar.interfaces.ApiResponseInterface;

import org.json.JSONObject;

public abstract class Networking {
    public abstract void post(String url, @Nullable JSONObject body, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface);
    public abstract void get(String url, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface);
    public abstract void patch(String url, @Nullable JSONObject body, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface);
    public abstract void delete(String url, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface);


}
