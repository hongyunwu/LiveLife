package com.why.livelife.View;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;

import com.why.livelife.R;
import com.why.livelife.Utils.DensityUtils;

/**
 * Created by lenovo on 2017/5/5.
 */

public class HomeActionSheet extends Dialog implements View.OnClickListener {

    private Button btn_home_action_sheet_live;
    private Button btn_home_action_sheet_video;
    private ImageButton ib_home_action_sheet_close;
    private OnHomeActionSheetClickListener onHomeActionSheetClickListener;
    private View rootView;

    public HomeActionSheet(Context context) {
        super(context);
        init(context);
    }


    public HomeActionSheet(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected HomeActionSheet(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }
    Handler handler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.2f, 1.2f, 1f);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.2f, 1.2f, 1f);
            PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.2f, 1f, 1f);
            float video_translationX = btn_home_action_sheet_video.getLeft();
            float video_translationY = btn_home_action_sheet_video.getTop();
            float live_translationX = btn_home_action_sheet_live.getLeft();
            float live_translationY = btn_home_action_sheet_live.getTop();
            float original_translationX = ib_home_action_sheet_close.getLeft() + ib_home_action_sheet_close.getWidth() / 2 - btn_home_action_sheet_video.getWidth()/2;
            float original_translationY = ib_home_action_sheet_close.getTop()/* + ib_home_action_sheet_close.getHeight() / 2*/;
            Log.i("HomeActionSheet","Visibility:"+(btn_home_action_sheet_video.getVisibility()==View.VISIBLE)+"video_right:"+video_translationX+",vedeo_top:"+video_translationY+",live_left:"+live_translationX+",live_top:"+live_translationY+",original_X:"+original_translationX+",original_Y:"+original_translationY);
            PropertyValuesHolder translateX_live = PropertyValuesHolder.ofFloat("x", original_translationX, live_translationX-20,live_translationX);
            PropertyValuesHolder translateY_live = PropertyValuesHolder.ofFloat("y", original_translationY, live_translationY-20,live_translationY);
            PropertyValuesHolder translateX_video = PropertyValuesHolder.ofFloat("x", original_translationX, video_translationX+20,video_translationX);
            PropertyValuesHolder translateY_video = PropertyValuesHolder.ofFloat("y", original_translationY, video_translationY-20,video_translationY);
            ObjectAnimator btn_home_action_sheet_live_animator = ObjectAnimator.ofPropertyValuesHolder(btn_home_action_sheet_live, scaleX, scaleY, alpha,translateX_live,translateY_live);
            ObjectAnimator btn_home_action_sheet_video_animator = ObjectAnimator.ofPropertyValuesHolder(btn_home_action_sheet_video, scaleX, scaleY, alpha,translateX_video,translateY_video);
            btn_home_action_sheet_live_animator.setDuration(600);
            btn_home_action_sheet_video_animator.setDuration(600);
            btn_home_action_sheet_live_animator.setInterpolator(new AccelerateDecelerateInterpolator());
            btn_home_action_sheet_video_animator.setInterpolator(new AccelerateDecelerateInterpolator());
        /*rootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);*/
            Log.i("HomeActionSheet","Visibility:"+(btn_home_action_sheet_video.getVisibility()==View.VISIBLE));
            btn_home_action_sheet_live_animator.start();
            btn_home_action_sheet_video_animator.start();
            Log.i("HomeActionSheet","Visibility:"+(btn_home_action_sheet_video.getVisibility()==View.VISIBLE));
            btn_home_action_sheet_live.setVisibility(View.VISIBLE);
            btn_home_action_sheet_video.setVisibility(View.VISIBLE);
        }
    };
    @Override
    public void show() {
        btn_home_action_sheet_live.setVisibility(View.INVISIBLE);
        btn_home_action_sheet_video.setVisibility(View.INVISIBLE);
        super.show();
        handler.sendEmptyMessageDelayed(250,100);


    }

    private void init(Context context) {
        rootView = View.inflate(context, R.layout.main_action_sheet, null);
        btn_home_action_sheet_live = (Button) rootView.findViewById(R.id.btn_home_action_sheet_live);
        btn_home_action_sheet_video = (Button) rootView.findViewById(R.id.btn_home_action_sheet_video);
        ib_home_action_sheet_close = (ImageButton) rootView.findViewById(R.id.ib_home_action_sheet_close);
        btn_home_action_sheet_live.setOnClickListener(this);
        btn_home_action_sheet_video.setOnClickListener(this);
        ib_home_action_sheet_close.setOnClickListener(this);
        setContentView(rootView);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.height = DensityUtils.dp2px(context,200);
        layoutParams.width = DensityUtils.getScreenWidth(context);
        rootView.requestLayout();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home_action_sheet_live:
                if (onHomeActionSheetClickListener!=null){
                    onHomeActionSheetClickListener.onLiveClick(btn_home_action_sheet_live);
                }
                break;
            case R.id.btn_home_action_sheet_video:
                if (onHomeActionSheetClickListener!=null){
                    onHomeActionSheetClickListener.onVideoClick(btn_home_action_sheet_video);
                }
                break;
            case R.id.ib_home_action_sheet_close:

                if (onHomeActionSheetClickListener!=null){
                    onHomeActionSheetClickListener.onCancel();
                }
                break;
            default:
                break;
        }
        dismiss();
    }
    public void setOnHomeActionSheetClickListener(OnHomeActionSheetClickListener onHomeActionSheetClickListener){
        this.onHomeActionSheetClickListener = onHomeActionSheetClickListener;
    }
    public interface OnHomeActionSheetClickListener {
        void onLiveClick(View view);
        void onVideoClick(View view);
        void onCancel();
    }
}
