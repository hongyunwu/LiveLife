package com.why.livelife.UI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

import com.why.livelife.Adapter.FansRecyleViewAdapter;
import com.why.livelife.Adapter.HomeRecyleViewAdapter;
import com.why.livelife.Decoration.StaggeredItemDecoration;
import com.why.livelife.R;
import com.why.livelife.Utils.DensityUtils;
import com.why.livelife.Utils.SPUtils;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Think on 2017/3/28.
 */
public class FansFragment extends BaseMainFragment implements View.OnClickListener {
    private static final String TAG = "FansFragment";
    private static final int REQUEST_READ_CONTACTS = 101;
    private static FansFragment fansFragment;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isRefreshing;
    private LinearLayout llNoUser;
    private Button btnContacts;
    private Button btnInterests;

    public static FansFragment getInstance(String name) {
        if (fansFragment == null) {
            fansFragment = new FansFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            fansFragment.setArguments(bundle);
        }

        return fansFragment;
    }

    ArrayList<String> list = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            swipeRefreshLayout.setRefreshing(false);
            isRefreshing = false;
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String name = getArguments().getString("name");
        for (int i = 0; i < 20; i++) {
            list.add(name + i);
        }
        Log.i(TAG, "onCreateView...");
        View inflate = inflater.inflate(R.layout.fragment_fans, null);
        swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.srl_home);
        llNoUser = (LinearLayout) inflate.findViewById(R.id.ll_fans_no_user);
        btnContacts = (Button) inflate.findViewById(R.id.btn_fans_contacts);
        btnInterests = (Button) inflate.findViewById(R.id.btn_fans_interests);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange_color, R.color.orange_color, R.color.orange_color, R.color.orange_color);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isRefreshing) {
                    isRefreshing = true;
                    handler.sendEmptyMessageDelayed(100, 2000);
                }
            }
        });
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recyleview_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new FansRecyleViewAdapter(getContext(), list));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                try {
                    if (swipeRefreshLayout != null) {
                        swipeRefreshLayout.setEnabled(isRecyclerViewReachTopEdge(recyclerView));
                        Log.i(TAG, "enabled:" + isRecyclerViewReachTopEdge(recyclerView));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Boolean fans_fragment_open = (Boolean) SPUtils.get(getContext(), "fans_fragment_open", false);
        if (fans_fragment_open) {
            swipeRefreshLayout.setVisibility(View.GONE);
            llNoUser.setVisibility(View.VISIBLE);
        } else {
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            llNoUser.setVisibility(View.GONE);
        }
        SPUtils.put(getContext(),"fans_fragment_open",!fans_fragment_open);

        btnContacts.setOnClickListener(this);
        btnInterests.setOnClickListener(this);
        return inflate;
    }

    private boolean isRecyclerViewReachTopEdge(RecyclerView recyclerView) {
        boolean result = false;
        if (((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() == 0) {
            final View topChildView = recyclerView.getChildAt(0);
            result = topChildView.getTop() <= 3;
        }
        return result;
    }

    public void scrollToTop() {
        recyclerView.getLayoutManager().scrollToPosition(0);
        isRefreshing = true;
        swipeRefreshLayout.setRefreshing(isRefreshing);
        handler.removeMessages(100);
        handler.sendEmptyMessageDelayed(100, 2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fans_contacts:
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    goToSubActivity();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{
                            Manifest.permission.READ_CONTACTS
                    }, REQUEST_READ_CONTACTS);
                }
                break;
            case R.id.btn_fans_interests:
                break;
            default:
                break;
        }
    }

    private void goToSubActivity() {
        Intent intent = new Intent(getContext(), ContactsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_READ_CONTACTS){
            if (grantResults.length>0){
                goToSubActivity();
            }else{
                Log.i(TAG,"拒绝读取通讯录权限...");
            }
        }
    }
}
