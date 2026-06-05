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
   @ColumnInfo(name = "messageId")
   private String messageId;
   @ColumnInfo(name = "isSent")
   private  boolean isSent;
   @ColumnInfo(name = "type")
   private String type;
   @ColumnInfo(name = "message")
    private  String message;
   @ColumnInfo(name ="timeStamp" )
    private  String timeStamp;
//this constructor is a default constructor which will be used by the system itself for giving out the response data

    public ChatSchema(int id, String conversationId,String messageId, boolean isSent,String type, String message, String timeStamp) {
        this.id = id;
        this.conversationId = conversationId;
        this.messageId=messageId;
        this.isSent = isSent;
        this.type=type;
        this.message = message;
        this.timeStamp = timeStamp;
    }

//    chat with conversation id and messageId for response recording

@Ignore
    public ChatSchema(String conversationId, String messageId, boolean isSent,String type, String message, String timeStamp) {
        this.conversationId = conversationId;
        this.type=type;
        this.messageId = messageId;
        this.isSent = isSent;
        this.message = message;
        this.timeStamp = timeStamp;
    }

//for chat with existing chatGroup

@Ignore
    public ChatSchema(String conversationId, boolean isSent,String type, String message, String timeStamp) {
        this.conversationId = conversationId;
        this.isSent = isSent;
        this.message = message;
        this.timeStamp = timeStamp;
        this.type=type;
    }

    //    this constructor will be used to add message before sending to the server
//    later when server acknowledges the request and provides a response which will have a unique key for this message
//    obtained from state
@Ignore
    public ChatSchema(boolean isSent,String type, String message, String timeStamp) {
        this.isSent = isSent;
        this.message = message;
        this.timeStamp = timeStamp;
        this.type=type;
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
    public String getMessageId() {
        return messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
