package com.why.livelife.View;

import android.content.Context;
import android.support.v4.view.ViewPager;  
import android.util.AttributeSet;  
import android.view.MotionEvent;  
  
public class NoScrollingViewPager extends ViewPager {
  
    private boolean noScroll = true;
  
    public NoScrollingViewPager(Context context) {
        super(context);  
    }  
  
    public NoScrollingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }
}
