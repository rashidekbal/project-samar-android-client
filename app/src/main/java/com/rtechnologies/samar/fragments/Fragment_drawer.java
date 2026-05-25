package com.rtechnologies.samar.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtechnologies.samar.databinding.FragmentDrawerBinding;

public class Fragment_drawer extends Fragment {
    FragmentDrawerBinding viewBinding;

    public Fragment_drawer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding=FragmentDrawerBinding.inflate(inflater,container,false);
        return viewBinding.getRoot();
    }
}