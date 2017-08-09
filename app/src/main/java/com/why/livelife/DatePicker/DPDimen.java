package com.why.livelife.DatePicker;

import android.content.Context;

import com.why.livelife.Utils.DensityUtils;

/**
 * Created by lenovo on 2017/5/9.
 */

public class DPDimen implements DPBaseDimen {
    private static final float TITLE_HEIGHT = 35;
    private Context context;

    public DPDimen(Context context) {
        this.context = context;
    }

    @Override
    public int getTitleHeight() {
        return DensityUtils.dp2px(context, TITLE_HEIGHT);
    }

    @Override
    public int getTitleTxtSize() {
        return DensityUtils.sp2px(context,15);
    }

    @Override
    public int getContentTxtSize() {
        return 0;
    }


}
