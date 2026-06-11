package com.rtechnologies.samar.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rtechnologies.samar.activity.MainActivity;
import com.rtechnologies.samar.adapters.Chat.MessageAdapter;
import com.rtechnologies.samar.constant.MessageType;
import com.rtechnologies.samar.databinding.FragmentChatBinding;
import com.rtechnologies.samar.interfaces.ChatServiceCallback;
import com.rtechnologies.samar.roomdb.schema.ChatSchema;
import com.rtechnologies.samar.service.ServiceProvider;
import com.rtechnologies.samar.utils.ToastUtil;
import com.rtechnologies.samar.viewModel.ChatGroupViewModel;
import com.rtechnologies.samar.viewModel.ChatViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fragment_chat extends Fragment {
    private FragmentChatBinding viewBinding;
    private List<ChatSchema> list;
    private MessageAdapter adapter;
    private String conversationId=null;
    private ChatViewModel chatViewModel;
    private MainActivity activity;
    private  String lastMessage="";
    private ChatGroupViewModel chatGroupViewModel;
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
        viewBinding.prePrompt1.setOnClickListener(this::handlePrePromptCardClick);
        viewBinding.prePrompt2.setOnClickListener(this::handlePrePromptCardClick);
        viewBinding.prePrompt3.setOnClickListener(this::handlePrePromptCardClick);
        viewBinding.prePrompt4.setOnClickListener(this::handlePrePromptCardClick);
        viewBinding.chatPauseBtn.setOnClickListener(this::handleSendMessageCancel);
        viewBinding.attachment.setOnClickListener(v-> Toast.makeText(requireActivity(),"coming soon",Toast.LENGTH_SHORT).show());
        viewBinding.newChatIcon.setOnClickListener(v->{activity.changeFragment(new Fragment_chat());
            activity.closeDrawer();
            chatGroupViewModel.setActiveConversationId("no id");
        });
    }

    private void setupRecyclerView() {
        viewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false));
        viewBinding.recyclerView.setAdapter(adapter);
    }

    private void init() {
        activity=(MainActivity) requireActivity();
        list=new ArrayList<>();
        adapter=new MessageAdapter(requireActivity(),list);
        chatViewModel=new ViewModelProvider(activity).get(ChatViewModel.class);
        chatGroupViewModel=new ViewModelProvider(activity).get(ChatGroupViewModel.class);
        if(getArguments()!=null){
            conversationId=getArguments().getString("cId");
            loadChats();
        }else{
            setUpNewChat();

        }

    }

    private void setUpToolbar(){
        AppCompatActivity activity=(AppCompatActivity) requireActivity();
        activity.setSupportActionBar( viewBinding.toolbar);
        Objects.requireNonNull(activity.getSupportActionBar()).setDisplayShowTitleEnabled(false);
        if(activity instanceof MainActivity){
            ((MainActivity) activity).setUpDrawerToggleWithToolBar(viewBinding.toolbar);
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private void loadChats(){
        if(conversationId==null){
        chatViewModel.getChats().observe(getViewLifecycleOwner(),list->{
          if(!list.isEmpty()){
              viewBinding.recyclerView.setVisibility(View.VISIBLE);
              viewBinding.newChatLayout.setVisibility(View.GONE);
              this.list.clear();
              this.list.addAll(list);
              adapter.notifyDataSetChanged();

              viewBinding.recyclerView.scrollToPosition(list.size()-1);
          }
        });}else{
            chatViewModel.getChats(conversationId).observe(getViewLifecycleOwner(),list->{
                if(!list.isEmpty()){
                    viewBinding.recyclerView.setVisibility(View.VISIBLE);
                    viewBinding.newChatLayout.setVisibility(View.GONE);
                    this.list.clear();
                    this.list.addAll(list);
                    adapter.notifyDataSetChanged();
                    viewBinding.recyclerView.scrollToPosition(list.size()-1);
                }
            });

        }
    }
    private void setUpNewChat(){
        viewBinding.recyclerView.setVisibility(View.GONE);
        viewBinding.newChatLayout.setVisibility(View.VISIBLE);
    }
    private void handleSendClick(View v){
        String input=viewBinding.messageInput.getText().toString().trim();
        if(input.isBlank())return;
        setStateChatCompleting();
        lastMessage=input;
        viewBinding.messageInput.setText("");
        if(conversationId==null){
             handleNewChat(input);

            return;
        }
        handleMessageSend(input);

    }

    private void setStateChatCompleting() {
        viewBinding.chatPauseBtn.setVisibility(View.VISIBLE);
        viewBinding.sendBtn.setVisibility(View.GONE);
    }
    private void setStateChatCompleted() {
        viewBinding.chatPauseBtn.setVisibility(View.GONE);
        viewBinding.sendBtn.setVisibility(View.VISIBLE);
    }

    private void handleMessageSend(String message) {

        ServiceProvider.chatService.newMessage(MessageType.TEXT.toString(), conversationId, message, new ChatServiceCallback() {
            @Override
            public void onNewChatSuccess(String conversation_id) {
                setStateChatCompleted();


            }

            @Override
            public void onFailure(String message) {
                setStateChatCompleted();
                viewBinding.messageInput.setText(lastMessage);
                ToastUtil.showToastShort(requireActivity(),"something went wrong...");


            }
        });
    }

    private void handleNewChat(String message) {
        ServiceProvider.chatService.newConversation(MessageType.TEXT.toString(), message, new ChatServiceCallback() {
            @Override
            public void onNewChatSuccess(String conversation_id) {
                setStateChatCompleted();
                conversationId=conversation_id;
                chatGroupViewModel.setActiveConversationId(conversation_id);
                loadChats();

            }

            @Override
            public void onFailure(String message) {
                setStateChatCompleted();
                viewBinding.messageInput.setText(lastMessage);
                ToastUtil.showToastShort(requireActivity(),"something went wrong...");



            }
        });
        loadChats();

    }

    private void handlePrePromptCardClick(View v){
        String prompt =((TextView)v).getText().toString().trim();
        if(prompt.isBlank())return;
        viewBinding.messageInput.setText(prompt);
        handleSendClick(viewBinding.sendBtn);

    }
    private void handleSendMessageCancel(View v){
        ServiceProvider.chatService.cancelMessageSending();
        setStateChatCompleted();
        viewBinding.messageInput.setText(lastMessage);


    }


}