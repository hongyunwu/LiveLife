package com.why.livelife.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.why.livelife.Adapter.HomeFragmentManager;
import com.why.livelife.Adapter.HomeTopTabAdapter;
import com.why.livelife.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/5/11.
 */

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.tl_top_tab)
    TabLayout tlTopTab;
    @BindView(R.id.tb_top_bar)
    Toolbar tbTopBar;
    @BindView(R.id.abl_top_bar_container)
    AppBarLayout ablTopBarContainer;
    @BindView(R.id.ib_menu)
    ImageButton ibMenu;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.vp_container)
    ViewPager vpContainer;
    private MainActivity mainActivity;
    private String[] tab_names;
    private static final String FRAGMNET_FANS = "关注";
    private static final String FRAGMNET_FIND = "发现";
    private static final String FRAGMNET_RECENT = "附近";
    private static HomeFragment homeFragment;

    public static HomeFragment getInstance(String title) {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", title);
            homeFragment.setArguments(bundle);
        }
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_home, container);

        ButterKnife.bind(this, inflate);
        mainActivity = (MainActivity) getActivity();
        tab_names = new String[]{"关注", "发现", "附近"};

        vpContainer.setAdapter(new HomeTopTabAdapter(getFragmentManager(), tab_names));
        vpContainer.setCurrentItem(1);
        tlTopTab.addTab(tlTopTab.newTab().setText(FRAGMNET_FANS));
        tlTopTab.addTab(tlTopTab.newTab().setText(FRAGMNET_FIND), true);
        tlTopTab.addTab(tlTopTab.newTab().setText(FRAGMNET_RECENT));
        tlTopTab.setupWithViewPager(vpContainer);
        return null;
    }

    @OnClick({R.id.ib_menu, R.id.ib_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_menu:
                mainActivity.openDrawer();
                break;
            case R.id.ib_search:
                Log.i(TAG, "搜索");
                goToSubActivity(SearchActivity.class);
                break;
        }

    }

    private void goToSubActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(getContext(), activity);
        startActivity(intent);
    }

    public void scrollToTop() {
        HomeFragmentManager.getInstance().getFragment(tab_names[vpContainer.getCurrentItem()],vpContainer.getCurrentItem()).scrollToTop();
    }
}
