package com.why.livelife.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.why.livelife.R;
import com.why.livelife.Utils.DensityUtils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2017/5/8.
 */
public class SearchRecycleViewTabAdapter extends RecyclerView.Adapter<SearchRecycleViewTabAdapter.SearchTabViewHolder> {
    private Random random = new Random();
    private int position;
    private Context context;

    public SearchRecycleViewTabAdapter(Context context, int position) {
        this.context = context;
        this.position = position;
    }

    @Override
    public SearchTabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchTabViewHolder searchTabViewHolder = new SearchTabViewHolder(LayoutInflater.from(context).inflate(R.layout.search_tab_scroll_item, parent, false));
        return searchTabViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchTabViewHolder holder, int position) {
        int nextInt = random.nextInt(3);
        switch (nextInt) {
            case 0:
                holder.ivSearchPortrait.setImageResource(R.drawable.search_tab_scroll_pic01);
                holder.tvSearchViewCount.setText(random.nextInt(2000) + "人");
                break;
            case 1:
                holder.ivSearchPortrait.setImageResource(R.drawable.search_tab_scroll_pic02);
                holder.tvSearchViewCount.setText(random.nextInt(2000) + "人");
                break;
            case 2:
                holder.ivSearchPortrait.setImageResource(R.drawable.search_tab_scroll_pic03);
                holder.tvSearchViewCount.setText(random.nextInt(2000) + "人");
                break;
            default:
                holder.ivSearchPortrait.setImageResource(R.drawable.search_tab_scroll_pic01);
                holder.tvSearchViewCount.setText(random.nextInt(2000) + "人");
                break;
        }
        if (position == 0) {
            holder.llSearchTabScroll.setPadding(DensityUtils.dp2px(context, 10), 0, 0, 0);
        } else {
            holder.llSearchTabScroll.setPadding(0, 0, 0, 0);
        }

    }

    @Override
    public int getItemCount() {
        return position + 4;
    }

    public class SearchTabViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_search_view_count)
        TextView tvSearchViewCount;
        @BindView(R.id.iv_search_portrait)
        ImageView ivSearchPortrait;
        @BindView(R.id.ll_search_tab_scroll)
        LinearLayout llSearchTabScroll;

        public SearchTabViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
