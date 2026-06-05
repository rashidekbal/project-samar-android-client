package com.rtechnologies.samar.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rtechnologies.samar.roomdb.Db;
import com.rtechnologies.samar.roomdb.DbHelper;
import com.rtechnologies.samar.roomdb.schema.ChatGroupSchema;
import com.rtechnologies.samar.roomdb.schema.ChatSchema;

import java.util.List;

public class ChatGroupViewModel extends AndroidViewModel {
    DbHelper dbHelper;
    public ChatGroupViewModel(@NonNull Application application) {
        super(application);
        this.dbHelper=new DbHelper(application);
    }
    public LiveData<List<ChatGroupSchema>> getConversations(){
        return Db.getInstance().chatGroupDao().getChatGroups();
    }
}
