package com.why.livelife.Decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

public class StaggeredItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "StaggeredItemDecoration";
    private int space;

    public StaggeredItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = space;
        outRect.bottom = space * 2;
        outRect.right = space;
        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = 0;
        }
    }

}