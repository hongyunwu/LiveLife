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

public class PrivateLetterFragment extends Fragment {
    private static final String TAG = "PrivateLetterFragment";
    private static PrivateLetterFragment privateLetterFragment;

    public static PrivateLetterFragment getInstance(String name) {
        if (privateLetterFragment == null) {
            privateLetterFragment = new PrivateLetterFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            privateLetterFragment.setArguments(bundle);
        }
        return privateLetterFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_private_letter, null);
        Log.i(TAG,"onCreateView");
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        privateLetterFragment = null;
    }
}
