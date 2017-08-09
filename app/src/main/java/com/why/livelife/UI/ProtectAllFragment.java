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

public class ProtectAllFragment extends Fragment {
    private static final String TAG = "ProtectAllFragment";
    private static ProtectAllFragment protectAllFragment;

    public static ProtectAllFragment getInstance(String name) {
        if (protectAllFragment == null) {
            protectAllFragment = new ProtectAllFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            protectAllFragment.setArguments(bundle);
        }
        return protectAllFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_protect_all, null);
        Log.i(TAG,"onCreateView");
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        protectAllFragment =null;
    }
}
