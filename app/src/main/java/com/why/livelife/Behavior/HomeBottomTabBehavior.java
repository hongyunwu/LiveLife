package com.why.livelife.Behavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.why.livelife.R;
import com.why.livelife.Utils.DensityUtils;

/**
 * Created by lenovo on 2017/5/4.
 */

public class HomeBottomTabBehavior extends CoordinatorLayout.Behavior<View> {

    private static final String TAG = "HomeBottomTabBehavior";
    private final int mScrollThreshold;
    private static final int DIRECTION_UP = 1;
    private static final int DIRECTION_DOWN = -1;
    private Context context;
    private int mScrollingDirection;
    private int mScrollDistance;
    private int mScrollTrigger;
    private ObjectAnimator mAnimator;
    private View dependencyView;

    public HomeBottomTabBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        this.context = context;
        TypedArray a = context.getTheme()
                .obtainStyledAttributes(new int[] {R.attr.actionBarSize});
        //滑动一半距离
        mScrollThreshold = a.getDimensionPixelSize(0, 0) / 2;
        a.recycle();

    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        Log.i(TAG,"onStopNestedScroll...");
        /*if (dependencyView.getHeight()<mScrollThreshold){
            restartAnimator(child, 0f);

        }else{
            restartAnimator(child, getTargetHideValue(coordinatorLayout, child));
        }*/
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.i(TAG,"onNestedFling...");

        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        Log.i(TAG,"onNestedPreFling...");
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.i(TAG,"onStartNestedScroll...");
        dependencyView = coordinatorLayout.findViewById(R.id.abl_top_bar_container);
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        mScrollDistance += dyConsumed;
        if (mScrollDistance >0
                && mScrollTrigger != DIRECTION_UP) {
            //Hide the target view
            mScrollTrigger = DIRECTION_UP;
            restartAnimator(child, getTargetHideValue(coordinatorLayout, child));
        } else if (mScrollDistance <0
                && mScrollTrigger != DIRECTION_DOWN) {
            //Return the target view
            mScrollTrigger = DIRECTION_DOWN;
            restartAnimator(child, 0f);
        }

        Log.i(TAG,"onNestedScroll..."+mScrollDistance+":"+mScrollThreshold);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.i(TAG,"onNestedPreScroll..."+"dx = "+dx+",dy = "+dy+",consumed = "+consumed.length+",0:"+consumed[0]+",1:"+consumed[1]);
        if (dy > 0 && mScrollingDirection != DIRECTION_UP) {
            mScrollingDirection = DIRECTION_UP;
            mScrollDistance = 0;
        } else if (dy < 0 && mScrollingDirection != DIRECTION_DOWN) {
            mScrollingDirection = DIRECTION_DOWN;
            mScrollDistance = 0;
        }
        mScrollDistance += dy;
        if (mScrollDistance >0
                && mScrollTrigger != DIRECTION_UP) {
            //Hide the target view
            mScrollTrigger = DIRECTION_UP;
            restartAnimator(child, getTargetHideValue(coordinatorLayout, child));
        } else if (mScrollDistance <0
                && mScrollTrigger != DIRECTION_DOWN) {
            //Return the target view
            mScrollTrigger = DIRECTION_DOWN;
            restartAnimator(child, 0f);
        }
        Log.i(TAG,"mScrollDistance:"+mScrollDistance);
    }


    /* Helper Methods */

    //Helper to trigger hide/show animation
    private void restartAnimator(View target, float value) {
        if (mAnimator != null) {
            mAnimator.cancel();
            mAnimator = null;
        }

        mAnimator = ObjectAnimator
                .ofFloat(target, View.TRANSLATION_Y, value)
                .setDuration(250);
        mAnimator.start();
    }

    private float getTargetHideValue(ViewGroup parent, View target) {
        return  dependencyView.getHeight()+ DensityUtils.dp2px(context,20);
    }


}
