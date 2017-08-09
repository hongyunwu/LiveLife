package com.why.livelife.UI;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.why.livelife.R;
import com.why.livelife.View.HomeActionSheet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final String FRAGMNET_FANS = "关注";
    private static final String FRAGMNET_FIND = "发现";
    private static final String FRAGMNET_RECENT = "附近";
    private static final String FRAGMNET_HOME = "首页";
    private static final String FRAGMNET_HISTORY = "历史";
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
    @BindView(R.id.cdl_container)
    CoordinatorLayout cdlContainer;
    @BindView(R.id.nv_left_container)
    NavigationView nvLeftContainer;
    @BindView(R.id.home_drawer)
    DrawerLayout homeDrawer;
    @BindView(R.id.ib_home_tab_main)
    ImageButton ibHomeTabMain;
    @BindView(R.id.ib_home_tab_history)
    ImageButton ibHomeTabHistory;
    @BindView(R.id.ib_home_tab_live)
    ImageButton ibHomeTabLive;
    @BindView(R.id.fl_container)
    FrameLayout mainContent;
    @BindView(R.id.title)
    TextView title;
    TextView tv_header_id;
    ImageView iv_header_circle_pic;
    TextView tv_header_dynamics;
    TextView tv_header_message;
    TextView tv_header_nick_name;
    TextView tv_header_private_letter;
    public static final String TAG = "MainActivity";
    private ValueAnimator ibHomeTabMainAnimator;
    private ObjectAnimator ibHomeTabRecentAnimator;
    private HomeActionSheet homeActionSheet;
    private FragmentManager fragmentManager;
    private Fragment mContent;
    private BaseMainFragment currentMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //setSupportActionBar(tbTopBar);
        nvLeftContainer.setItemIconTintList(null);
        NavigationMenuView navigationMenuView = (NavigationMenuView) nvLeftContainer.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
            navigationMenuView.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
        }
        View headerView = nvLeftContainer.getHeaderView(0);
        tv_header_dynamics = (TextView) headerView.findViewById(R.id.tv_header_dynamics);
        tv_header_id = (TextView) headerView.findViewById(R.id.tv_header_id);
        tv_header_message = (TextView) headerView.findViewById(R.id.tv_header_message);
        tv_header_nick_name = (TextView) headerView.findViewById(R.id.tv_header_nick_name);
        tv_header_private_letter = (TextView) headerView.findViewById(R.id.tv_header_private_letter);
        iv_header_circle_pic = (ImageView) headerView.findViewById(R.id.iv_header_circle_pic);
        tv_header_private_letter.setOnClickListener(this);
        tv_header_message.setOnClickListener(this);
        tv_header_dynamics.setOnClickListener(this);
        nvLeftContainer.setNavigationItemSelectedListener(this);
        ibHomeTabMain.setOnClickListener(this);
        ibHomeTabHistory.setOnClickListener(this);
        ibHomeTabLive.setOnClickListener(this);
        ibMenu.setOnClickListener(this);
        ibSearch.setOnClickListener(this);
        /*vpContainer.setAdapter(new HomeTopTabAdapter(getSupportFragmentManager(), tab_names));
        tlTopTab.setupWithViewPager(vpContainer);*/
        tlTopTab.addTab(tlTopTab.newTab().setText(FRAGMNET_FANS));
        tlTopTab.addTab(tlTopTab.newTab().setText(FRAGMNET_FIND),true);
        tlTopTab.addTab(tlTopTab.newTab().setText(FRAGMNET_RECENT));
        tlTopTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabTitle = tab.getText().toString();
                if (FRAGMNET_FANS.equals(tabTitle)) {
                    currentMainFragment = FansFragment.getInstance(FRAGMNET_FANS);
                } else if (FRAGMNET_FIND.equals(tabTitle)) {
                    currentMainFragment = FindFragment.getInstance(FRAGMNET_FIND);
                } else if (FRAGMNET_RECENT.equals(tabTitle)) {
                    currentMainFragment = RecentFragment.getInstance(FRAGMNET_RECENT);
                }
                replaceFragment(currentMainFragment);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        fragmentManager = getSupportFragmentManager();
        currentMainFragment = FindFragment.getInstance(FRAGMNET_FIND);
        addFragment(currentMainFragment);
    }

    /**
     * 添加fragment
     */
    public Fragment addFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_container, fragment).commit();
        } else {
            transaction.show(fragment).commit();
        }

        mContent = fragment;

        return fragment;
    }

    /**
     * 替换fragment
     */
    public Fragment replaceFragment(Fragment desFragment) {

        if (mContent != desFragment) {

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            if (!desFragment.isAdded()) {
                transaction.hide(mContent).add(R.id.fl_container, desFragment).commit();
            } else {
                transaction.hide(mContent).show(desFragment).commit();
            }
            mContent = desFragment;
        }
        return desFragment;
    }

    private long[] mHits = new long[2];

    @Override
    public void onBackPressed() {
        if (homeDrawer.isDrawerOpen(Gravity.LEFT)){
            closeDrawer();
            return;
        }
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);

        mHits[mHits.length - 1] = SystemClock.uptimeMillis();

        if (SystemClock.uptimeMillis() - mHits[0] <= 2000) {
            finish();
        } else {
            Toast.makeText(this, "再点一次退出Live Life", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_header_private_letter:
                Log.i(TAG, "私信");
                Intent private_letter = new Intent(this, MessageActivity.class);
                private_letter.putExtra("message_category","private_letter");
                startActivity(private_letter);
                break;
            case R.id.tv_header_message:
                Log.i(TAG, "消息");
                Intent message = new Intent(this, MessageActivity.class);
                message.putExtra("message_category","message");
                startActivity(message);
                break;
            case R.id.tv_header_dynamics:
                Log.i(TAG, "动态");
                Intent dynamics = new Intent(this, MessageActivity.class);
                dynamics.putExtra("message_category","dynamics");
                startActivity(dynamics);
                break;
            case R.id.ib_menu:
                Log.i(TAG, "菜单");
                openDrawer();
                break;
            case R.id.ib_search:
                Log.i(TAG, "搜索");
                goToSubActivity(SearchActivity.class);
                break;
            case R.id.ib_home_tab_main:
                if (ibHomeTabMainAnimator == null) {
                    PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.2f, 1f);
                    PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.2f, 1f);
                    ibHomeTabMainAnimator = ObjectAnimator.ofPropertyValuesHolder(ibHomeTabMain, scaleX, scaleY);
                    ibHomeTabMainAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    ibHomeTabMainAnimator.setDuration(300);
                }
                ibHomeTabMainAnimator.start();
                replaceFragment(currentMainFragment);
                title.setVisibility(View.INVISIBLE);
                tlTopTab.setVisibility(View.VISIBLE);
                tlTopTab.setEnabled(true);
                if (ibHomeTabMain.isSelected())
                    currentMainFragment.scrollToTop();
                ibHomeTabMain.setSelected(true);
                ibHomeTabHistory.setSelected(false);
                break;
            case R.id.ib_home_tab_history:
                if (ibHomeTabRecentAnimator == null) {
                    PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.2f, 1f);
                    PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.2f, 1f);
                    ibHomeTabRecentAnimator = ObjectAnimator.ofPropertyValuesHolder(ibHomeTabHistory, scaleX, scaleY);
                    ibHomeTabRecentAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    ibHomeTabRecentAnimator.setDuration(300);
                }
                ibHomeTabRecentAnimator.start();
                title.setVisibility(View.VISIBLE);
                tlTopTab.setVisibility(View.INVISIBLE);
                tlTopTab.setEnabled(false);
                HistoryFragment historyFragment = HistoryFragment.getInstance(FRAGMNET_HISTORY);
                replaceFragment(historyFragment);
                if (ibHomeTabHistory.isSelected())
                    historyFragment.scrollToTop();
                ibHomeTabMain.setSelected(false);
                ibHomeTabHistory.setSelected(true);
                break;
            case R.id.ib_home_tab_live:
                if (homeActionSheet == null) {
                    homeActionSheet = new HomeActionSheet(this, R.style.ActionSheetDialogStyle);
                }
                homeActionSheet.show();
                Log.i(TAG, "点击直播按钮...");
                break;
            default:
                break;
        }
    }

    public void openDrawer() {
        if (!homeDrawer.isDrawerOpen(Gravity.LEFT)) {
            homeDrawer.openDrawer(Gravity.LEFT);
        }
    }

    private void goToSubActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navi_item_protect:
                Intent intent = new Intent(this, ProtectActivity.class);
                intent.putExtra("nick_name","乌啼夜的酒痕");
                startActivity(intent);
                break;
            case R.id.navi_item_account:
                break;
            case R.id.navi_item_certification:
                break;
            case R.id.navi_item_fans:
                closeDrawer();
                if (currentMainFragment==mContent){
                    //说明在home页
                    tlTopTab.getTabAt(0).select();
                }else{
                    title.setVisibility(View.INVISIBLE);
                    tlTopTab.setVisibility(View.VISIBLE);
                    tlTopTab.setEnabled(true);
                    ibHomeTabMain.setSelected(true);
                    ibHomeTabHistory.setSelected(false);
                    tlTopTab.getTabAt(0).select();
                }

                break;
            case R.id.navi_item_level:
                break;
            case R.id.navi_item_money:
                break;
            case R.id.navi_item_scan:
                break;
            case R.id.navi_item_setting:
                break;
            case R.id.navi_item_video:
                break;
            default:
                break;
        }
        /*closeDrawer();*/

        return true;
    }

    private void closeDrawer() {
        if (homeDrawer.isDrawerOpen(Gravity.LEFT))
            homeDrawer.closeDrawer(Gravity.LEFT);
    }
}
