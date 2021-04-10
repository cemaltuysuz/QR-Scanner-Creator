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

import com.thic.qrreadercreator.Model.adapters.adapterGenerate;
import com.thic.qrreadercreator.Model.adapters.adapterScan;
import com.thic.qrreadercreator.Model.model;
import com.thic.qrreadercreator.R;
import com.thic.qrreadercreator.Viewmodel.QrViewmodel;

import java.util.List;

public class generateList extends Fragment {

    private QrViewmodel viewmodel;
    private RecyclerView recyclerViewG;
    private adapterGenerate adapterG;

    public generateList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(requireActivity()).get(QrViewmodel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_generate_list, container, false);

        recyclerViewG = root.findViewById(R.id.recyclerview_GenerateList);
        recyclerViewG.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewG.setHasFixedSize(true);
        adapterG = new adapterGenerate();
        recyclerViewG.setAdapter(adapterG);
        QrViewmodel.actionModeİsActive.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                adapterGenerate.actionActive = aBoolean;
                adapterG.notifyDataSetChanged();
            }
        });
        viewmodel.getAllGenerateModel().observe(getViewLifecycleOwner(), new Observer<List<model>>() {
            @Override
            public void onChanged(List<model> models) {
                adapterG.setQR(models);
            }
        });

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewmodel.setSingleSelect(false);
    }
}