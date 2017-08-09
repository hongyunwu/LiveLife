package com.why.livelife.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.why.livelife.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindingPhoneActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.title)
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_phone);
        ButterKnife.bind(this);
        title.setText("绑定手机");

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
