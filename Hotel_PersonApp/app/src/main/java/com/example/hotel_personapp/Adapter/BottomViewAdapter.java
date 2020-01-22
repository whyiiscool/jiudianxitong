package com.example.hotel_personapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class BottomViewAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public BottomViewAdapter(FragmentManager fm,List<Fragment> FragmentList) {
        super(fm);
        mFragmentList = FragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
