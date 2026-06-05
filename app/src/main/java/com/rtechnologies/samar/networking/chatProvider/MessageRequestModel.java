package com.rtechnologies.samar.networking.chatProvider;

public class MessageRequestModel extends NewMessageRequestModel {
    String conversation_id;

    public MessageRequestModel(String conversation_id,String message_id, String message) {
        super(message_id, message);
        this.conversation_id=conversation_id;
    }

}
