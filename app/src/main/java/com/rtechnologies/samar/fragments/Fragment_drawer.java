package com.rtechnologies.samar.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rtechnologies.samar.activity.MainActivity;
import com.rtechnologies.samar.adapters.HistoryAdapter;
import com.rtechnologies.samar.databinding.FragmentDrawerBinding;
import com.rtechnologies.samar.roomdb.schema.ChatGroupSchema;
import com.rtechnologies.samar.utils.DateUtil;
import com.rtechnologies.samar.viewModel.ChatGroupViewModel;

import java.util.ArrayList;
import java.util.List;

public class Fragment_drawer extends Fragment {
    HistoryAdapter adapter;
    List<ChatGroupSchema> historyList;
    FragmentDrawerBinding viewBinding;
    MainActivity activity;
    ChatGroupViewModel conversationHistoryViewModel;

    public Fragment_drawer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding=FragmentDrawerBinding.inflate(inflater,container,false);
        init();
        observeConversation();
        setUpRecyclerView();
        setOnClickListeners();
        return viewBinding.getRoot();
    }


    private void init() {
        historyList=new ArrayList<>();
        activity=(MainActivity) requireActivity();
        adapter=new HistoryAdapter(requireActivity(),historyList, this::itemSelected);
        conversationHistoryViewModel=new ViewModelProvider(activity).get(ChatGroupViewModel.class);
    }
    private void observeConversation(){
        conversationHistoryViewModel.getConversations().observe(getViewLifecycleOwner(),list->{
            if(list!=null&&!list.isEmpty()){
                historyList.clear();
                historyList.addAll(list);
                adapter.notifyItemRangeChanged(0,list.size());
            }
        });
    }

    private void setUpRecyclerView() {
        viewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false));
        viewBinding.recyclerView.setAdapter(adapter);
    }
    @SuppressLint("NotifyDataSetChanged")
    private void setOnClickListeners() {
        viewBinding.newConversationCardBtn.setOnClickListener(v-> {
            activity.changeFragment(new Fragment_chat());
            activity.closeDrawer();
            adapter.selectedId=-1;
            adapter.notifyDataSetChanged();

        });

    }

    @SuppressLint("NotifyDataSetChanged")
    private void itemSelected(int position){
        adapter.notifyDataSetChanged();
        Toast.makeText(requireActivity(),historyList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
//            TODO: implement open chat feature from here
            activity.closeDrawer();
            activity.changeFragment(new Fragment_chat().getInstance(historyList.get(position).getConversationId()));

    }


}