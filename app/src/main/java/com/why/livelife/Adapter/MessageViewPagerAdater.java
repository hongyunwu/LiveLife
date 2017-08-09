package com.why.livelife.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.why.livelife.UI.DynamicsFragment;
import com.why.livelife.UI.MessageFragment;
import com.why.livelife.UI.PrivateLetterFragment;

/**
 * Created by lenovo on 2017/5/9.
 */
public class MessageViewPagerAdater extends FragmentStatePagerAdapter {
    private static final String TAG = "MessageViewPagerAdater";

    public MessageViewPagerAdater(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "动态";
                break;
            case 1:
                title = "消息";
                break;
            case 2:
                title = "私信";
                break;
            default:
                break;
        }
        return title;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = DynamicsFragment.getInstance("动态");
                break;
            case 1:
                fragment = MessageFragment.getInstance("消息");
                break;
            case 2:
                fragment = PrivateLetterFragment.getInstance("私信");
                break;
            default:
                break;
        }
        Log.i(TAG,"position:"+position);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }


}
