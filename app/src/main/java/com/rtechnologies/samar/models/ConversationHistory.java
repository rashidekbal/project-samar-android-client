package com.rtechnologies.samar.models;

public class ConversationHistory {
    private int id;
    private String conversationId;
    private String title;


    public ConversationHistory(int id, String title, String conversationId) {
        this.id = id;
        this.title = title;
        this.conversationId = conversationId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
