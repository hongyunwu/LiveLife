package com.why.livelife.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.why.livelife.R;

/**
 * Created by lenovo on 2017/5/9.
 */

public class ProtectWeekFragment extends Fragment {
    private static final String TAG = "ProtectWeekFragment";
    private static ProtectWeekFragment protectWeekFragment;

    public static ProtectWeekFragment getInstance(String name) {
        if (protectWeekFragment == null) {
            protectWeekFragment = new ProtectWeekFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            protectWeekFragment.setArguments(bundle);
        }
        return protectWeekFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_protect_week, null);
        Log.i(TAG,"onCreateView");
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        protectWeekFragment =null;
    }
}
