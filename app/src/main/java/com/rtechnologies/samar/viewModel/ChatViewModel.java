package com.rtechnologies.samar.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ChatViewModel extends AndroidViewModel {
    private String conversationId;
    public ChatViewModel(@NonNull Application application) {
        super(application);
    }
}
