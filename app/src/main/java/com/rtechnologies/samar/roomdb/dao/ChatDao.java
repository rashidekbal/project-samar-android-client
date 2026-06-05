package com.rtechnologies.samar.roomdb.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rtechnologies.samar.roomdb.schema.ChatGroupSchema;
import com.rtechnologies.samar.roomdb.schema.ChatSchema;

import java.util.List;

@Dao
public interface ChatDao {
    @Query("select * from chats where conversationId=:conversationId order by id asc")
    LiveData<List<ChatSchema>> getChats(String conversationId);
    @Query("select * from chats where conversationId is null order by id desc limit 1")
    LiveData<List<ChatSchema>> getUnClammedChat();
    @Insert
    long insertChat(ChatSchema chatSchema);
    @Query("update chats set messageId=:message_id, conversationId=:conversation_id where id=:id")
    void updateMessageId(long id,String message_id,String conversation_id);



}
