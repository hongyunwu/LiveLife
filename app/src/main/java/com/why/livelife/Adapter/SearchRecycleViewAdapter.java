package com.why.livelife.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.why.livelife.Decoration.SearchTabItemDecoration;
import com.why.livelife.R;
import com.why.livelife.Utils.DensityUtils;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by lenovo on 2017/5/8.
 */
public class SearchRecycleViewAdapter extends RecyclerView.Adapter<SearchRecycleViewAdapter.SearchViewHolder> {
    private static final int SEARCH_TAB_ITEM = 101;
    private static final int SEARCH_COMMON_ITEM = 102;


    private Context context;
    private ArrayList<String> list;

    public SearchRecycleViewAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchViewHolder holder = null;
        switch (viewType) {
            case SEARCH_COMMON_ITEM:
                holder = new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.search_recycleview_item, parent, false), viewType);
                break;
            case SEARCH_TAB_ITEM:
                holder = new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.search_recycleview_tab_item, parent, false), viewType);
                holder.recycleviewSearchTabScroll.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                holder.recycleviewSearchTabScroll.addItemDecoration(new SearchTabItemDecoration(context, SearchTabItemDecoration.HORIZONTAL_LIST));
                holder.recycleviewSearchTabScroll.setAdapter(new SearchRecycleViewTabAdapter(context, new Random().nextInt(4)));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case SEARCH_COMMON_ITEM:
                if (position == 3) {
                    holder.tvSearchTodayIntroduce.setVisibility(View.VISIBLE);
                } else {

                    holder.tvSearchTodayIntroduce.setVisibility(View.GONE);
                }
                int resId = 0;
                if (position % 2 == 0) {
                    resId = R.drawable.contacts_item_mark;
                    holder.ivSearchItemPortrait.setBackgroundResource(R.drawable.search_common_item_portrait01);
                    holder.tvSearchItemNickName.setText("乌啼夜的酒痕");
                    holder.tvSearchItemName.setText("伍洪云");
                } else {
                    resId = R.drawable.contacts_item_plus;
                    holder.ivSearchItemPortrait.setBackgroundResource(R.drawable.search_common_item_portrait02);
                    holder.tvSearchItemNickName.setText("傻妞儿");
                    holder.tvSearchItemName.setText("张国靖");
                }
                Drawable drawable = context.getResources().getDrawable(resId);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//需设置图片大小
                holder.tvSearchItemAdd.setCompoundDrawables(drawable, null, null, null);
                break;
            case SEARCH_TAB_ITEM:
                if (position % 3 == 0) {
                    holder.tvSearchTabTitle.setText("娱乐明星");
                } else if (position % 3 == 1) {
                    holder.tvSearchTabTitle.setText("游戏达人");

                } else {
                    holder.tvSearchTabTitle.setText("音乐小王子");
                }


                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position < 3) {
            return SEARCH_TAB_ITEM;
        }
        return SEARCH_COMMON_ITEM;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSearchItemPortrait;
        TextView tvSearchItemNickName;
        TextView tvSearchItemName;
        TextView tvSearchItemAdd;

        TextView tvSearchTabTitle;
        Button btnTabSearchMore;
        RecyclerView recycleviewSearchTabScroll;
        CardView cardviewSearchTab;
        RelativeLayout searchTabItem;
        TextView tvSearchTodayIntroduce;

        public SearchViewHolder(View itemView, int viewType) {
            super(itemView);
            switch (viewType) {
                case SEARCH_COMMON_ITEM:
                    ivSearchItemPortrait = (ImageView) itemView.findViewById(R.id.iv_search_item_portrait);
                    tvSearchItemNickName = (TextView) itemView.findViewById(R.id.tv_search_item_nick_name);
                    tvSearchItemName = (TextView) itemView.findViewById(R.id.tv_search_item_name);
                    tvSearchItemAdd = (TextView) itemView.findViewById(R.id.tv_search_item_add);
                    tvSearchTodayIntroduce = (TextView) itemView.findViewById(R.id.tv_search_today_introduce);
                    break;
                case SEARCH_TAB_ITEM:
                    tvSearchTabTitle = (TextView) itemView.findViewById(R.id.tv_search_tab_title);
                    btnTabSearchMore = (Button) itemView.findViewById(R.id.btn_tab_search_more);
                    recycleviewSearchTabScroll = (RecyclerView) itemView.findViewById(R.id.recycleview_search_tab_scroll);
                    cardviewSearchTab = (CardView) itemView.findViewById(R.id.cardview_search_tab);
                    searchTabItem = (RelativeLayout) itemView.findViewById(R.id.search_tab_item);
                    break;
            }
        }

    }
}
