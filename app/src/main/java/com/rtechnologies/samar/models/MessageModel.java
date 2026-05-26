package com.rtechnologies.samar.models;

import com.rtechnologies.samar.constant.MessageType;

public class MessageModel {
    private int id;
    private String type;
    private String messageUid;
    private String conversationId;
    private String message;
    private boolean isSent;

    public MessageModel(int id, String type, String messageUid, String conversationId, String message, boolean isSent) {
        this.id = id;
        this.type = type;
        this.messageUid = messageUid;
        this.conversationId = conversationId;
        this.message = message;
        this.isSent = isSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageUid() {
        return messageUid;
    }

    public void setMessageUid(String messageUid) {
        this.messageUid = messageUid;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }
}
