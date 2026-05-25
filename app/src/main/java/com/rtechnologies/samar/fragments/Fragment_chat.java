package com.rtechnologies.samar.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtechnologies.samar.activity.MainActivity;
import com.rtechnologies.samar.databinding.FragmentChatBinding;

import java.util.Objects;

public class Fragment_chat extends Fragment {
    FragmentChatBinding viewBinding;
    public Fragment_chat() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding=FragmentChatBinding.inflate(inflater,container,false);
        setUpToolbar();
     return viewBinding.getRoot();
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