package com.rtechnologies.samar.networking;

public class NetworkingHelper {
    public NetworkingModule nm;
    public NetworkingHelper(NetworkingModule nm){
        this.nm=nm;

    }
    public void cancelRequest(String Tag){
        nm.cancelRequest(Tag);
    }

}
