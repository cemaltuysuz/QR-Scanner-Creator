package com.thic.qrreadercreator.UI.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.thic.qrreadercreator.Model.adapters.adapterGenerate;
import com.thic.qrreadercreator.Model.adapters.adapterScan;
import com.thic.qrreadercreator.Model.adapters.slideAdapter;
import com.thic.qrreadercreator.Model.model;
import com.thic.qrreadercreator.R;
import com.thic.qrreadercreator.Viewmodel.QrViewmodel;

import java.util.ArrayList;
import java.util.List;

public class homeListFragment extends Fragment {

    private slideAdapter adapter;
    private ViewPager pager;
    private AppCompatImageView closeListHome;
    private List<Fragment> fmList;
    private TabLayout tabLayout;
    private QrViewmodel viewmodel;
    private Toolbar toolbar;

    //Adapters Control
    private adapterScan scanAdapter;
    private adapterGenerate generateAdapter;

    public homeListFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        fmList = new ArrayList<>();
        fmList.add(new scanList());
        fmList.add(new generateList());
        adapter = new slideAdapter(getParentFragmentManager(),fmList);
        viewmodel = new ViewModelProvider(this).get(QrViewmodel.class);
        scanAdapter = new adapterScan();
        generateAdapter = new adapterGenerate();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_list, container, false);
        pager = root.findViewById(R.id.viewPager);
        tabLayout = root.findViewById(R.id.tablLayout);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(0).setText(R.string.scanList);
        tabLayout.getTabAt(1).setText(R.string.generateList);
        toolbar = root.findViewById(R.id.homeListToolbar);
        
        QrViewmodel.actionModeİsActive.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean==true)toolbar.setVisibility(View.VISIBLE);
                else toolbar.setVisibility(View.GONE);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.toolbarIconClose:
                        QrViewmodel.actionModeİsActive.setValue(false);
                        adapterScan.selectedListAdapter.clear();
                        generateAdapter.selectedItemList.clear();
                        scanAdapter.notifyDataSetChanged();
                        break;

                        case R.id.toolbarIconDelete:
                        List<model>newList = new ArrayList<>();
                        newList.addAll(adapterScan.selectedListAdapter);
                        newList.addAll(adapterGenerate.selectedItemList);
                        if (newList.size()>0){
                          for (int i =0;i<newList.size();i++){
                              viewmodel.del(newList.get(i));
                          }
                          newList.clear();
                          QrViewmodel.actionModeİsActive.setValue(false);
                          adapterScan.selectedListAdapter.clear();
                          adapterGenerate.selectedItemList.clear();
                        }
                }
                return false;
            }
        });

        viewmodel.actionModeİsActive.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    toolbar.setVisibility(View.VISIBLE);
                }else{
                    toolbar.setVisibility(View.GONE);
                }
            }
        });
        closeListHome = root.findViewById(R.id.homeCloseList);

        closeListHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.listhome_to_home);
            }
        });

        viewmodel.getSingleSelect().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Navigation.findNavController(root).navigate(R.id.action_homeListFragment_to_bottomSheet);
                }
            }
        });
        return root;
    }
}