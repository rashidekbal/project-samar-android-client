package com.rtechnologies.samar.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rtechnologies.samar.roomdb.Db;
import com.rtechnologies.samar.roomdb.DbHelper;
import com.rtechnologies.samar.roomdb.schema.ChatSchema;
import com.rtechnologies.samar.utils.Logger;

import java.util.List;

public class ChatViewModel extends AndroidViewModel {
    DbHelper dbHelper;
    public ChatViewModel(@NonNull Application application) {
        super(application);
        this.dbHelper=new DbHelper(application);

    }
    LiveData<List<ChatSchema>> chats=new MutableLiveData<>();

    public LiveData<List<ChatSchema>> getChats(String conversationId){
        return Db.getInstance().chatDao().getChats(conversationId);
    }
    public LiveData<List<ChatSchema>> getChats(){
        return Db.getInstance().chatDao().getUnClammedChat();

    }
}
