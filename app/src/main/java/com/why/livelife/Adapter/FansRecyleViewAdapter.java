package com.why.livelife.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.why.livelife.R;

import java.util.ArrayList;

public class FansRecyleViewAdapter extends RecyclerView.Adapter<FansRecyleViewAdapter.MyViewHolder> {
    private ArrayList<String> list;
    private Context context;

    public FansRecyleViewAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.fans_recyleview_item, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int resId = 0;
        switch (position % 2) {
            case 0:
                resId = R.drawable.fans_item_portrait;
                break;
            case 1:
                resId = R.drawable.fans_item_portrait1;
                break;
            default:
                resId = R.drawable.fans_item_portrait1;
                break;
        }
        holder.imageView.setBackgroundResource(resId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView = null;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_fans_item_portrait);
        }
    }
}