package com.why.livelife.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.why.livelife.R;

import java.util.ArrayList;

public class HomeRecyleViewAdapter extends RecyclerView.Adapter<HomeRecyleViewAdapter.MyViewHolder>{
	private ArrayList<String> list;
	private Context context;

	public HomeRecyleViewAdapter(Context context, ArrayList<String> list){
			this.context = context;
		this.list = list;
		}
		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			MyViewHolder myViewHolder = new MyViewHolder(View.inflate(context, R.layout.find_recyleview_item, null));
			return myViewHolder;
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, int position) {
			int resId = 0;
				switch (position%3){
					case 0:
						resId = R.drawable.thumbpic_01;
						break;
					case 1:
						resId = R.drawable.thumbpic_02;
						break;
					case 2:
						resId = R.drawable.thumbpic_03;
						break;
					default:
						resId = R.drawable.thumbpic_03;
						break;
				}
			holder.textView.setBackgroundResource(resId);
		}

		@Override
		public int getItemCount() {
			return list.size();
		}

		class MyViewHolder extends RecyclerView.ViewHolder{
			ImageView textView = null;
			public MyViewHolder(View itemView) {
				super(itemView);
				textView = (ImageView) itemView.findViewById(R.id.iv_live_thumb_pic);
			}
		}
	}