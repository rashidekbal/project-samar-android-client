package com.rtechnologies.samar.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rtechnologies.samar.roomdb.Db;
import com.rtechnologies.samar.roomdb.DbHelper;
import com.rtechnologies.samar.roomdb.schema.ChatGroupSchema;


import java.util.List;

public class ChatGroupViewModel extends AndroidViewModel {
    DbHelper dbHelper;
    private final MutableLiveData<String> conversation_id;
    public ChatGroupViewModel(@NonNull Application application) {
        super(application);
        this.dbHelper=new DbHelper(application);
        this.conversation_id=new MutableLiveData<>();

    }

    public LiveData<List<ChatGroupSchema>> getConversations(){
        return Db.getInstance().chatGroupDao().getChatGroups();
    }
    public LiveData<String> getActiveConversationId(){
        return conversation_id;
    }
    public void setActiveConversationId(String id){
        conversation_id.setValue(id);
    }
}
