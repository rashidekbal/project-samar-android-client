package com.rtechnologies.samar.interfaces;

import androidx.lifecycle.LiveData;

import com.rtechnologies.samar.roomdb.schema.ChatGroupSchema;

import java.util.List;

public interface ChatGroupLiveDataCallback {
    void onConversationsLoaded(LiveData<List<ChatGroupSchema>> list);
}

