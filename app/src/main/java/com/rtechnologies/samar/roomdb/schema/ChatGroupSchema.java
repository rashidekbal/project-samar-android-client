package com.rtechnologies.samar.roomdb.schema;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "chatGroups",
indices = {@Index(value = {"conversationId"},unique = true)})
public class ChatGroupSchema {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name ="conversationId")
    private String conversationId;
    @ColumnInfo(name ="title")
    private  String title;
    @ColumnInfo(name ="timeStamp")
    private  String timeStamp;

//  Default constructor for fetching the data through query


    public ChatGroupSchema(int id, String conversationId, String title, String timeStamp) {
        this.id = id;
        this.conversationId = conversationId;
        this.title = title;
        this.timeStamp = timeStamp;
    }

    //  used to create new conversation when no id is present but server will make one and provide
    @Ignore
    public ChatGroupSchema( String conversationId, String title, String timeStamp) {
        this.conversationId = conversationId;
        this.title = title;
        this.timeStamp = timeStamp;
    }

    public String getTitle() {
        return title;
    }


    public String getTimeStamp() {
        return timeStamp;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
