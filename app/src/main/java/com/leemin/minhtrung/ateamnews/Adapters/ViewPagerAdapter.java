package com.leemin.minhtrung.ateamnews.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.leemin.minhtrung.ateamnews.Fragments.MyFragment;
import com.leemin.minhtrung.ateamnews.bean.Category;

import java.util.ArrayList;

/**
 * Created by Admin on 5/6/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Category> tabs;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Category> tabs) {
        super(fm);
        this.tabs = tabs;
    }
    @Override
    public Fragment getItem(int i) {
        return MyFragment.newInstance(tabs.get(i));
    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}
