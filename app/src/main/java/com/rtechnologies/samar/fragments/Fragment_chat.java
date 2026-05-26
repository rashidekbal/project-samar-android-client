package com.rtechnologies.samar.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
    FragmentChatBinding viewBinding;
    ArrayList<MessageModel> list;
    MessageAdapter adapter;
    public Fragment_chat() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding=FragmentChatBinding.inflate(inflater,container,false);
        setUpToolbar();
        init();
        setupRecyclerView();
     return viewBinding.getRoot();
    }

    private void setupRecyclerView() {
        viewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false));
        viewBinding.recyclerView.setAdapter(adapter);
    }

    private void init() {
        list=new ArrayList<>();
//        TODO:add dynamic handling via viewModel
        list.add(new MessageModel(1, MessageType.TEXT.toString(),"xyz","abc","hi",true));
        list.add(new MessageModel(2, MessageType.TEXT.toString(),"xyz1s","abc","hello how can i help you today",false));
        list.add(new MessageModel(3, MessageType.TEXT.toString(),"xyz2s","abc","tell me about how to make an app",true));
        list.add(new MessageModel(4, MessageType.TEXT.toString(),"xyz3s","abc","here is a simple app that prints hello world in python 'print('hello')'",false));
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

}