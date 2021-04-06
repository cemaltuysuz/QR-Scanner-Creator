package com.thic.qrreadercreator.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thic.qrreadercreator.Model.adapters.slideAdapter;
import com.thic.qrreadercreator.R;
import com.thic.qrreadercreator.Viewmodel.QrViewmodel;

public class generateList extends Fragment {

    private QrViewmodel viewmodel;

    public generateList() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_generate_list, container, false);
    }
}