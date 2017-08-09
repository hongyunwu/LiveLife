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

public class MessageFragment extends Fragment {
    private static final String TAG = "MessageFragment";
    private static MessageFragment messageFragment;

    public static MessageFragment getInstance(String name) {
        if (messageFragment == null) {
            messageFragment = new MessageFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            messageFragment.setArguments(bundle);
        }
        return messageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_message, null);
        Log.i(TAG,"onCreateView");
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        messageFragment=null;
    }
}
