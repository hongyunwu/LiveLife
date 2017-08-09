package com.why.livelife.Adapter;

import com.why.livelife.UI.BaseMainFragment;
import com.why.livelife.UI.FansFragment;
import com.why.livelife.UI.FindFragment;
import com.why.livelife.UI.HomeFragment;
import com.why.livelife.UI.RecentFragment;

/**
 * Created by lenovo on 2017/5/11.
 */

public class HomeFragmentManager {
    private HomeFragmentManager (){

    }
    private static HomeFragmentManager homeFragmentManager;
    public static HomeFragmentManager getInstance(){
        if (homeFragmentManager==null){
            synchronized (HomeFragmentManager.class){
                if (homeFragmentManager==null){
                    homeFragmentManager = new HomeFragmentManager();
                }
            }
        }
        return homeFragmentManager;
    }
    public BaseMainFragment getFragment(String tabName,int position){
        BaseMainFragment fragment;
        if (position==0){
            fragment = FansFragment.getInstance(tabName);
        }else if (position==1){
            fragment = FindFragment.getInstance(tabName);

        }else{
            fragment = RecentFragment.getInstance(tabName);
        }
        return fragment;
    }
}
