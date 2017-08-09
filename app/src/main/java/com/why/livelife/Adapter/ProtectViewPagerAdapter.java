package com.why.livelife.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.why.livelife.UI.ProtectAllFragment;
import com.why.livelife.UI.ProtectWeekFragment;

/**
 * Created by lenovo on 2017/5/9.
 */
public class ProtectViewPagerAdapter extends FragmentStatePagerAdapter {
    public ProtectViewPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return ProtectWeekFragment.getInstance("总榜");
        }else if (position==1){
            return ProtectAllFragment.getInstance("总榜");
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "周榜";
        }else if (position==1){
            return "总榜";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
