package com.rtechnologies.samar.interfaces;

public interface ChatServiceCallback {
    void onNewChatSuccess(String conversation_id);
    void onFailure(String message);
}
