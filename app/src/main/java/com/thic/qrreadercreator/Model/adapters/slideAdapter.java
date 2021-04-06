package com.thic.qrreadercreator.Model.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.thic.qrreadercreator.UI.fragments.scanList;

import java.util.ArrayList;
import java.util.List;

public class slideAdapter extends FragmentStatePagerAdapter {

    private List<Fragment>fragmentList;
    public slideAdapter(@NonNull FragmentManager fm,List<Fragment>fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
