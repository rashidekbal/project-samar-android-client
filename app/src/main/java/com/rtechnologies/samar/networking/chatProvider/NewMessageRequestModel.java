package com.rtechnologies.samar.networking.chatProvider;

public class NewMessageRequestModel {
    public NewMessageRequestModel(String message_id, String message) {
        this.message_id = message_id;
        this.message = message;
    }


    private String message_id;
    private String message;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
