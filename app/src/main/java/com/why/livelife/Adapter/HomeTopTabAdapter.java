package com.why.livelife.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.why.livelife.UI.BaseMainFragment;
import com.why.livelife.UI.FansFragment;
import com.why.livelife.UI.FindFragment;
import com.why.livelife.UI.RecentFragment;

public  class HomeTopTabAdapter extends FragmentPagerAdapter {
    private String[] tab_names;

    public HomeTopTabAdapter(FragmentManager supportFragmentManager, String[] tab_names) {
        super(supportFragmentManager);
        this.tab_names = tab_names;
    }

    @Override
        public CharSequence getPageTitle(int position) {
            return tab_names[position];
        }


        @Override
        public BaseMainFragment getItem(int position) {
            return HomeFragmentManager.getInstance().getFragment(tab_names[position],position);
        }

        @Override
        public int getCount() {
            return tab_names.length;
        }
    }