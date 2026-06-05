package com.rtechnologies.samar.roomdb.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rtechnologies.samar.roomdb.schema.ChatGroupSchema;

import java.util.List;

@Dao
public interface ChatGroupDao {
    @Query("select * from chatGroups order by id desc")
    LiveData<List<ChatGroupSchema>> getChatGroups();
    @Insert
    void insertChatGroup(ChatGroupSchema chatGroupSchema);

}
