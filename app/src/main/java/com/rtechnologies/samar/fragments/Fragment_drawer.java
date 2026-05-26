package com.rtechnologies.samar.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rtechnologies.samar.activity.MainActivity;
import com.rtechnologies.samar.adapters.HistoryAdapter;
import com.rtechnologies.samar.databinding.FragmentDrawerBinding;
import com.rtechnologies.samar.models.ConversationHistory;

import java.util.ArrayList;

public class Fragment_drawer extends Fragment {
    HistoryAdapter adapter;
    ArrayList<ConversationHistory> historyList;
    FragmentDrawerBinding viewBinding;
    MainActivity activity;

    public Fragment_drawer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding=FragmentDrawerBinding.inflate(inflater,container,false);
        init();
        setUpRecyclerView();
        setOnClickListeners();
        return viewBinding.getRoot();
    }


    private void init() {
        activity=(MainActivity) requireActivity();
        historyList=new ArrayList<>();
        historyList.add(new ConversationHistory(1,"how to make a app for creating embedding","xyz"));
        historyList.add(new ConversationHistory(2,"write a program...","xz"));
        historyList.add(new ConversationHistory(3,"Agentic ai workflow","yz"));
        adapter=new HistoryAdapter(requireActivity(),historyList, this::itemSelected);


    }

    private void setUpRecyclerView() {
        viewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false));
        viewBinding.recyclerView.setAdapter(adapter);
    }
    @SuppressLint("NotifyDataSetChanged")
    private void setOnClickListeners() {
        viewBinding.newConversationCardBtn.setOnClickListener(v-> {
            activity.changeFragment(new Fragment_chat(), null);
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
            activity.changeFragment(new Fragment_chat(),null);

    }


}