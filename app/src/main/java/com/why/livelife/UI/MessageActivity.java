package com.why.livelife.UI;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.why.livelife.Adapter.MessageViewPagerAdater;
import com.why.livelife.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends AppCompatActivity {

    private static final String TAG = "MessageActivity";
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tl_top_tab)
    TabLayout tlTopTab;
    @BindView(R.id.search_tool_bar)
    Toolbar searchToolBar;
    @BindView(R.id.vp_container)
    ViewPager vpContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        String message_category = getIntent().getStringExtra("message_category");
        Log.i(TAG, message_category);
        boolean dynamics = message_category.equals("dynamics");
        tlTopTab.addTab(tlTopTab.newTab().setText("动态"));
        boolean message = message_category.equals("message");
        tlTopTab.addTab(tlTopTab.newTab().setText("消息"));
        boolean private_letter = message_category.equals("private_letter");
        tlTopTab.addTab(tlTopTab.newTab().setText("私信"));

        vpContainer.setOffscreenPageLimit(0);
        vpContainer.setAdapter(new MessageViewPagerAdater(getSupportFragmentManager()));

        //tlTopTab.setupWithViewPager(vpContainer);
        if (dynamics) {
            vpContainer.setCurrentItem(0);
            tlTopTab.getTabAt(0).select();
            tlTopTab.setScrollPosition(0,0,true);
        } else if (message) {
            vpContainer.setCurrentItem(1);
            tlTopTab.setScrollPosition(1,1,true);
            tlTopTab.getTabAt(1).select();
        } else if (private_letter) {
            vpContainer.setCurrentItem(2);
            tlTopTab.getTabAt(2).select();
        }
        vpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tlTopTab.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tlTopTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @OnClick(R.id.ib_back)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                finish();
                break;
            default:
                break;
        }
    }
}
