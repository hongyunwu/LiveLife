package com.why.livelife.DatePicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.why.livelife.R;
import com.why.livelife.Utils.DensityUtils;

/**
 * Created by lenovo on 2017/5/11.
 */

public class DPCalendarView extends LinearLayout {
    private static final String TAG = "DPCalendarView";
    private int mWidth;
    private int mHeight;
    private int dateHeight;
    private Drawable mAnchorDrawable;
    private int mPaddingAnchor;
    private int mAnchorWidth;
    private int mPaddingRight;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG |
            Paint.LINEAR_TEXT_FLAG);
    private int mAnchorBG;

    public DPCalendarView(Context context) {
        super(context);
        init();
    }

    public DPCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DPCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){
        mAnchorDrawable = getResources().getDrawable(R.drawable.down_anchor);
        mPaddingAnchor = DensityUtils.dp2px(getContext(),3);
        mPaddingRight = DensityUtils.dp2px(getContext(),10);
        mAnchorWidth = mPaddingAnchor+mAnchorDrawable.getIntrinsicWidth();
        mAnchorBG = getResources().getColor(R.color.orange_color);
        setBackgroundResource(android.R.color.transparent);
        setWillNotDraw(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        View childAt = getChildAt(getChildCount() - 1);
        dateHeight = childAt.getHeight();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAnchor(canvas);

    }

    private void drawAnchor(Canvas canvas) {
        mPaint.setColor(mAnchorBG);
        Log.i(TAG,"centerX:"+(mWidth - mPaddingRight-mAnchorWidth/2)+",centerY:"+(mHeight - dateHeight)+",radius:"+mAnchorWidth);
        canvas.drawCircle(100,100,mAnchorWidth,mPaint);

    }
}
