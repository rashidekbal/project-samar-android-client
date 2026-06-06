package com.rtechnologies.samar.networking;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.rtechnologies.samar.interfaces.ApiResponseInterface;
import com.rtechnologies.samar.networking.abstractClasses.Networking;

import org.json.JSONObject;

public class NetworkingModule extends Networking {
    @Override
    public void post(@Nullable String TAG,String url, @Nullable JSONObject body, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface) {
        AndroidNetworking.post(url).setPriority(Priority.HIGH).addJSONObjectBody(body).addHeaders("Authorization","Bearer "+JwtToken).setTag(TAG).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject jsonObject) {
               apiResponseInterface.onSuccess(jsonObject);
            }

            @Override
            public void onError(ANError anError) {
                apiResponseInterface.onError(anError);

            }
        });
    }

    @Override
    public void get(@Nullable String TAG,String url, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface) {
        AndroidNetworking.get(url).setPriority(Priority.HIGH).addHeaders("Authorization","Bearer "+JwtToken).setTag(TAG).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                apiResponseInterface.onSuccess(response);
            }

            @Override
            public void onError(ANError anError) {

            }
        });

    }

    @Override
    public void patch(@Nullable String TAG,String url, @Nullable JSONObject body, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface) {
        AndroidNetworking.patch(url).setPriority(Priority.HIGH).addJSONObjectBody(body).addHeaders("Authorization","Bearer "+JwtToken).setTag(TAG).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                apiResponseInterface.onSuccess(jsonObject);
            }

            @Override
            public void onError(ANError anError) {
                apiResponseInterface.onError(anError);

            }
        });
    }

    @Override
    public void delete(@Nullable String TAG,String url, @Nullable String JwtToken, ApiResponseInterface apiResponseInterface) {
        AndroidNetworking.delete(url).setPriority(Priority.HIGH).addHeaders("Authorization","Bearer "+JwtToken).setTag(TAG).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                apiResponseInterface.onSuccess(jsonObject);
            }

            @Override
            public void onError(ANError anError) {
                apiResponseInterface.onError(anError);

            }
        });
    }

    @Override
    public void cancelRequest(String Tag) {
        AndroidNetworking.cancel(Tag);
    }
}
