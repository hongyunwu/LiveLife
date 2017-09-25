package com.why.livelife.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.why.livelife.Adapter.HistoryRecyleViewAdapter;
import com.why.livelife.DatePicker.DPMonthView;
import com.why.livelife.Decoration.GridSpacingItemDecoration;
import com.why.livelife.R;
import com.why.livelife.Utils.DensityUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Think on 2017/3/28.
 */
public class HistoryFragment extends BaseMainFragment implements DPMonthView.OnDatePickerClickListener {
    private static final String TAG = "HistoryFragment";
    private static HistoryFragment historyFragment;
    private boolean isAnchorDown = true;
    private RotateAnimation downAnimation;
    private RotateAnimation upAnimation;

    public static HistoryFragment getInstance(String name) {
        if (historyFragment == null) {
            historyFragment = new HistoryFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            historyFragment.setArguments(bundle);
        }

        return historyFragment;
    }

    @BindView(R.id.dp_calendar_content)
    DPMonthView dpMonthView;
    @BindView(R.id.tv_history_date)
    TextView tvDate;
    @BindView(R.id.recycleview_history)
    RecyclerView recyclerView;
    @BindView(R.id.ib_anchor)
    ImageButton ibAnchor;
    ArrayList<String> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String name = getArguments().getString("name");
        for (int i = 0; i < 30; i++) {
            list.add(name + i);
        }
        Log.i(TAG, "onCreateView...");
        View inflate = inflater.inflate(R.layout.fragment_history, null);
        ButterKnife.bind(this, inflate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        tvDate.setText(getDateStr(format));
        dpMonthView.setOnDatePickClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, DensityUtils.dp2px(getContext(), 3), false));
        recyclerView.setAdapter(new HistoryRecyleViewAdapter(getContext(), list));

        return inflate;
    }

    @OnClick({R.id.ib_anchor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_anchor:
                if (!isAnchorDown) {
                    dpMonthView.closeCalendar();
                    if (downAnimation == null){
                        downAnimation = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        downAnimation.setFillAfter(true);
                    }

                    ibAnchor.startAnimation(downAnimation);

                } else {

                    if (upAnimation == null){
                        upAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        upAnimation.setFillAfter(true);
                    }
                    ibAnchor.startAnimation(upAnimation);
                    dpMonthView.openCalendar();
                }
                isAnchorDown = !isAnchorDown;

                break;
            default:
                break;

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDatePick(String date) {

        tvDate.setText(getDateStr(date));
        Toast.makeText(getContext(), date, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMonthChanged(int year, int month) {
        Log.i(TAG, "onMonthChanged...year:"+year+",month:"+month);
    }

    public String getDateStr(String dateStr) {
        String[] split = dateStr.split("-");
        return split[1] + "月" + split[2] + "日";
    }
}
