package com.rtechnologies.samar.interfaces;

import com.androidnetworking.error.ANError;

import org.json.JSONObject;

public interface ApiResponseInterface {
    void onSuccess(JSONObject response);
    void onError(ANError error);
}
