package com.thic.qrreadercreator.UI.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.thic.qrreadercreator.Model.QrRepository;
import com.thic.qrreadercreator.Model.adapters.recyclerViewAdapter;
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
    TabLayout tabLayout;
    QrViewmodel viewmodel;
    private Toolbar toolbar;
    private recyclerViewAdapter viewAdapter;

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
        viewAdapter = new recyclerViewAdapter();
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
                        recyclerViewAdapter.selectedListAdapter.clear();
                        viewAdapter.notifyDataSetChanged();
                        toolbar.setTitle("");
                        break;
                    case R.id.toolbarIconShare:
                        share();
                        break;
                    case R.id.toolbarIconDelete:
                        List<model>newList = recyclerViewAdapter.selectedListAdapter;
                        if (newList.size()>0){
                          for (int i =0;i<newList.size();i++){
                              viewmodel.del(newList.get(i));
                          }
                          newList.clear();
                          recyclerViewAdapter.selectedListAdapter.clear();
                          toolbar.setTitle("");
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

        QrViewmodel.getSelectedList().observe(getViewLifecycleOwner(), new Observer<List<model>>() {
            @Override
            public void onChanged(List<model> models) {
                Toast.makeText(getActivity(),String.valueOf(models.size()),Toast.LENGTH_SHORT).show();
                toolbar.setTitle(viewmodel.toolbarTitle());
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
    public void share(){

    }
}