package com.rtechnologies.samar.service;
import com.rtechnologies.samar.networking.ApiProvider;
import com.rtechnologies.samar.service.abstractClasses.ChatBluePrint;

public class ServiceProvider {
    public static ChatBluePrint chatService =new ChatService(ApiProvider.chatApiProvider);
}
