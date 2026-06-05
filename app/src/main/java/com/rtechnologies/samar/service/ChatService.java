package com.rtechnologies.samar.service;

import com.rtechnologies.samar.networking.ChatApiProvider;
import com.rtechnologies.samar.service.abstractClasses.ChatBluePrint;

public class ChatService extends ChatBluePrint {
    ChatApiProvider chatApiProvider;

    public ChatService(ChatApiProvider apiProvider){
        this.chatApiProvider=apiProvider;

    }

    @Override
    public String newChat() {

        return "";
    }

    @Override
    public void newMessage() {

    }
}
