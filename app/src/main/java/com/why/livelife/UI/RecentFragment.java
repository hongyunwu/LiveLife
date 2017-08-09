package com.why.livelife.UI;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.why.livelife.Adapter.HomeRecyleViewAdapter;
import com.why.livelife.Adapter.RecentRecyleViewAdapter;
import com.why.livelife.Decoration.StaggeredItemDecoration;
import com.why.livelife.R;
import com.why.livelife.Utils.DensityUtils;

import java.util.ArrayList;

/**
 * Created by Think on 2017/3/28.
 */
public class RecentFragment extends BaseMainFragment {
    private static final String TAG = "RecentFragment";
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isRefreshing;
    private static RecentFragment recentFragment;

    public static RecentFragment getInstance(String name) {
        if (recentFragment == null){
            recentFragment = new RecentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            recentFragment.setArguments(bundle);
        }

        return recentFragment;
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
        Log.i(TAG,"onCreateView...");
        View inflate = inflater.inflate(R.layout.fragment_recent, null);
        swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.srl_home);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new RecentRecyleViewAdapter(getContext(), list));
        recyclerView.addItemDecoration(new StaggeredItemDecoration(DensityUtils.dp2px(getContext(), 3)));
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
        return inflate;
    }

    private boolean isRecyclerViewReachTopEdge(RecyclerView recyclerView) {
        boolean result = false;
        int firstVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisibleItems == 0) {
            final View topChildView = recyclerView.getChildAt(0);
            result = topChildView.getTop() <= 3;
        }
        return result;
    }
    public void scrollToTop(){
        recyclerView.scrollToPosition(0);
        isRefreshing = true;
        swipeRefreshLayout.setRefreshing(isRefreshing);
        handler.removeMessages(100);
        handler.sendEmptyMessageDelayed(100,2000);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
