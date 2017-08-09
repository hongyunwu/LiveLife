package com.why.livelife.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.why.livelife.Adapter.SearchRecycleViewAdapter;
import com.why.livelife.R;
import com.why.livelife.Utils.KeyBoardUtils;
import com.why.livelife.View.ClearEditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.search_tool_bar)
    Toolbar searchToolBar;
    @BindView(R.id.recycleview_search_content)
    RecyclerView recycleviewSearchContent;
    @BindView(R.id.activity_search)
    LinearLayout activitySearch;
    @BindView(R.id.et_search)
    ClearEditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
        recycleviewSearchContent.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recycleviewSearchContent.setAdapter(new SearchRecycleViewAdapter(this,list));
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH){
                    KeyBoardUtils.closeKeybord(etSearch,SearchActivity.this);
                }
                return false;
            }
        });

    }

    @OnClick(R.id.btn_cancel)
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                finish();
                break;
            default:
                break;
        }
    }
}
