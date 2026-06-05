package com.rtechnologies.samar.roomdb.schema;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "chats", foreignKeys = {
        @ForeignKey(entity = ChatGroupSchema.class,parentColumns = "conversationId",childColumns = "conversationId",onDelete =ForeignKey.CASCADE)
})
public class ChatSchema {
   @PrimaryKey(autoGenerate = true)
    private int id;
   @ColumnInfo(name = "conversationId",index = true)
    private String conversationId;
   @ColumnInfo(name = "isSent")
   private  boolean isSent;
   @ColumnInfo(name = "message")
    private  String message;
   @ColumnInfo(name ="timeStamp" )
    private  String timeStamp;
//this constructor is a default constructor which will be used by the system itself for giving out the response data

    public ChatSchema(int id, String conversationId, boolean isSent, String message, String timeStamp) {
        this.id = id;
        this.conversationId = conversationId;
        this.isSent = isSent;
        this.message = message;
        this.timeStamp = timeStamp;
    }


//    this constructor will be used to add message before sending to the server
//    later when server acknowledges the request and provides a response which will have a unique key for this message
//    obtained from state
@Ignore
    public ChatSchema(boolean isSent, String message, String timeStamp) {
        this.isSent = isSent;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public boolean isSent() {
        return isSent;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
