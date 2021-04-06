package com.thic.qrreadercreator.UI.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.thic.qrreadercreator.Model.adapters.slideAdapter;
import com.thic.qrreadercreator.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class homeListFragment extends Fragment {

    private slideAdapter adapter;
    private ViewPager pager;
    private AppCompatImageView closeListHome;
    private List<Fragment> fmList;
    TabLayout tabLayout;


    public homeListFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fmList = new ArrayList<>();
        fmList.add(new scanList());
        fmList.add(new generateList());
        adapter = new slideAdapter(getParentFragmentManager(),fmList);
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

        closeListHome = root.findViewById(R.id.homeCloseList);

        closeListHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.listhome_to_home);
            }
        });

        return root;
    }
}