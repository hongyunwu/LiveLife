package com.why.livelife.UI;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.why.livelife.Adapter.ProtectViewPagerAdapter;
import com.why.livelife.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProtectActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tb_top_bar)
    Toolbar tbTopBar;
    @BindView(R.id.tl_protect_list)
    TabLayout tlProtectList;
    @BindView(R.id.vp_container)
    ViewPager vpContainer;
    @BindView(R.id.activity_protect)
    LinearLayout activityProtect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect);
        ButterKnife.bind(this);
        title.setText(getIntent().getStringExtra("nick_name")+"的守护榜");
        tlProtectList.addTab(tlProtectList.newTab().setText("周榜"));
        tlProtectList.addTab(tlProtectList.newTab().setText("总榜"));
        vpContainer.setAdapter(new ProtectViewPagerAdapter(getSupportFragmentManager()));
        tlProtectList.setupWithViewPager(vpContainer);
    }

    @OnClick(R.id.back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
