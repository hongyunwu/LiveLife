package com.why.livelife.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.why.livelife.R;

/**
 * Created by lenovo on 2017/5/9.
 */

public class DynamicsFragment extends Fragment {
    private static final String TAG = "DynamicsFragment";
    private static DynamicsFragment dynamicsFragment;

    public static DynamicsFragment getInstance(String name) {
        if (dynamicsFragment == null) {
            dynamicsFragment = new DynamicsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            dynamicsFragment.setArguments(bundle);
        }
        return dynamicsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_dynamics, null);
        Log.i(TAG,"onCreateView");
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dynamicsFragment=null;
    }
}
