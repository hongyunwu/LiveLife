package com.why.livelife.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.why.livelife.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsRecyleViewAdapter extends RecyclerView.Adapter<ContactsRecyleViewAdapter.MyViewHolder> {

    private ArrayList<String> list;
    private Context context;

    public ContactsRecyleViewAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.contacts_recyleview_item, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int resId = 0;
        switch (position % 2) {
            case 0:
                resId = R.drawable.contacts_item_mark;
                break;
            case 1:
                resId = R.drawable.contacts_item_plus;
                break;
            default:
                resId = R.drawable.contacts_item_mark;
                break;
        }
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//需设置图片大小
        holder.tvContactsItemAdd.setCompoundDrawables(drawable,null,null,null);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_contacts_item_portrait)
        ImageView ivContactsItemPortrait;
        @BindView(R.id.tv_contacts_item_nick_name)
        TextView tvContactsItemNickName;
        @BindView(R.id.tv_contacts_item_name)
        TextView tvContactsItemName;
        @BindView(R.id.tv_contacts_item_add)
        TextView tvContactsItemAdd;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}