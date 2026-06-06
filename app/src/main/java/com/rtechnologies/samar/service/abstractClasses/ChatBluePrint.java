package com.rtechnologies.samar.service.abstractClasses;

import com.rtechnologies.samar.interfaces.ChatServiceCallback;

public abstract class ChatBluePrint {
    public abstract void newConversation(String type,String message, ChatServiceCallback callback);
    public abstract void newMessage(String type,String conversation_id,String message,ChatServiceCallback callback);
    public abstract void cancelMessageSending();
}
