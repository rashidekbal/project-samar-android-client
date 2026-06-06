package com.rtechnologies.samar.roomdb;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.rtechnologies.samar.interfaces.ChatGroupLiveDataCallback;
import com.rtechnologies.samar.interfaces.ChatLiveDataCallback;
import com.rtechnologies.samar.interfaces.DbInsertionCallback;
import com.rtechnologies.samar.roomdb.schema.ChatGroupSchema;
import com.rtechnologies.samar.roomdb.schema.ChatSchema;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DbHelper {
    ExecutorService executorService;
    public DbHelper(Context context){
        this.executorService= Executors.newSingleThreadExecutor();
    }

    public void insertChat(boolean isSent,String type, String message, String timeStamp, DbInsertionCallback callback){
        executorService.execute(()->{
            ChatSchema chat=new ChatSchema(isSent,type,message,timeStamp);
            long insertId=Db.getInstance().chatDao().insertChat(chat);
            callback.onInsertSuccess(insertId);
        });
    }
    public void insertChat(boolean isSent,String type,String conversation_id,String message,String timeStamp,DbInsertionCallback callback){
        executorService.execute(()->{
            ChatSchema chat=new ChatSchema(conversation_id,isSent,type,message,timeStamp);
            long insertId=Db.getInstance().chatDao().insertChat(chat);
            callback.onInsertSuccess(insertId);
        });

    }
    public void insertChat(boolean isSent,String type,String conversation_id,String message_id,String message,String timeStamp){
        executorService.execute(()->{
            ChatSchema chat=new ChatSchema(conversation_id,message_id,isSent,type,message,timeStamp);
            Db.getInstance().chatDao().insertChat(chat);
        });

    }
    public void updateMessageId(long id,String message_id,String conversation_id){
        executorService.execute(()->{
            Db.getInstance().chatDao().updateMessageId(id,message_id,conversation_id);
        });

    }

    public void getChats(String conversationId,ChatLiveDataCallback callback){
        executorService.execute(()->{
            callback.onChatsLoaded(Db.getInstance().chatDao().getChats(conversationId));
        });
    }
    public void remove_chatsWithoutParent(){
        executorService.execute(()->{
            Db.getInstance().chatDao().delete_chat_without_parent();
        });
    }




//    chat group handling section
    public void insertNewGroup(String conversation_id,String title,String time_stamp){
        executorService.execute(()->{
            ChatGroupSchema chatGroupSchema=new ChatGroupSchema(conversation_id,title,time_stamp);
            Db.getInstance().chatGroupDao().insertChatGroup(chatGroupSchema);
        });


    }

    public void getConversations(ChatGroupLiveDataCallback callback){
        executorService.execute(()->{
            callback.onConversationsLoaded(Db.getInstance().chatGroupDao().getChatGroups());
        });
    }


}
