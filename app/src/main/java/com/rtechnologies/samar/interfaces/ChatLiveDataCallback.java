package com.rtechnologies.samar.interfaces;

import androidx.lifecycle.LiveData;

import com.rtechnologies.samar.roomdb.schema.ChatSchema;

import java.util.List;

public interface ChatLiveDataCallback {
    void onChatsLoaded(LiveData<List<ChatSchema>> list);
}
