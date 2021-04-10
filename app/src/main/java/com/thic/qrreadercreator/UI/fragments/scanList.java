package com.thic.qrreadercreator.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thic.qrreadercreator.Model.adapters.adapterScan;
import com.thic.qrreadercreator.Model.model;
import com.thic.qrreadercreator.R;
import com.thic.qrreadercreator.Viewmodel.QrViewmodel;

import java.util.List;

public class scanList extends Fragment {

    private RecyclerView recyclerViewS;
    private adapterScan adapterS;
    private QrViewmodel viewmodel;

    public scanList() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(requireActivity()).get(QrViewmodel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scan_list, container, false);

        recyclerViewS = root.findViewById(R.id.recyclerview_ScanList);
        recyclerViewS.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewS.setHasFixedSize(true);
        adapterS = new adapterScan();
        recyclerViewS.setAdapter(adapterS);

        viewmodel.getAllScanModel().observe(getViewLifecycleOwner(), new Observer<List<model>>() {
            @Override
            public void onChanged(List<model> models) {
                adapterS.setQR(models);
            }
        });

        QrViewmodel.actionModeİsActive.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                adapterScan.actionActive = aBoolean;
                adapterS.notifyDataSetChanged();
            }
        });

        return root;
    }
}