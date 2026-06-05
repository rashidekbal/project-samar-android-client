package com.rtechnologies.samar.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtechnologies.samar.activity.MainActivity;
import com.rtechnologies.samar.adapters.Chat.MessageAdapter;
import com.rtechnologies.samar.constant.MessageType;
import com.rtechnologies.samar.databinding.FragmentChatBinding;
import com.rtechnologies.samar.models.MessageModel;

import java.util.ArrayList;
import java.util.Objects;

public class Fragment_chat extends Fragment {
    private FragmentChatBinding viewBinding;
    private ArrayList<MessageModel> list;
    private MessageAdapter adapter;
    private String conversationId=null;
    public Fragment_chat() {
        // Required empty public constructor
    }
    public Fragment_chat getInstance(@Nullable String conversationId){
        Fragment_chat fragment=new Fragment_chat();
        Bundle bundle=new Bundle();
        bundle.putString("cId",conversationId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding=FragmentChatBinding.inflate(inflater,container,false);
        setUpToolbar();
        init();
        setupRecyclerView();
        setupEventListeners();
     return viewBinding.getRoot();
    }

    private void setupEventListeners() {
        viewBinding.sendBtn.setOnClickListener(this::handleSendClick);
    }

    private void setupRecyclerView() {
        viewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false));
        viewBinding.recyclerView.setAdapter(adapter);
    }

    private void init() {
        if(getArguments()!=null){
            conversationId=getArguments().getString("cId");
            loadChats();
        }else{
            setUpNewChat();

        }
        list=new ArrayList<>();
        adapter=new MessageAdapter(requireActivity(),list);
    }

    private void setUpToolbar(){
        AppCompatActivity activity=(AppCompatActivity) requireActivity();
        activity.setSupportActionBar((androidx.appcompat.widget.Toolbar) viewBinding.toolbar);
        Objects.requireNonNull(activity.getSupportActionBar()).setDisplayShowTitleEnabled(false);
        if(activity instanceof MainActivity){
            ((MainActivity) activity).setUpDrawerToggleWithToolBar(viewBinding.toolbar);
        }
    }
    private void loadChats(){
//        add chat loading for existing conversation
    }
    private void setUpNewChat(){
        viewBinding.recyclerView.setVisibility(View.GONE);
        viewBinding.newChatLayout.setVisibility(View.VISIBLE);
    }
    private void handleSendClick(View v){
        String input=viewBinding.messageInput.getText().toString().trim();
        if(input.isBlank())return;
        if(conversationId==null){
             handleNewChat(input);
            return;
        }
        handleMessageSend(input);

    }

    private void handleMessageSend(String message) {
//        service call for sending message

    }

    private void handleNewChat(String message) {
//        service call for creating new chat and setting up its cid in fragment

    }

}