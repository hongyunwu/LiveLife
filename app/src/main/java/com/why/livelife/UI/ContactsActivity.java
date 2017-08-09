package com.why.livelife.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.why.livelife.Adapter.ContactsRecyleViewAdapter;
import com.why.livelife.R;
import com.why.livelife.View.BindPhoneDiaLog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactsActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tb_top_bar)
    Toolbar tbTopBar;
    @BindView(R.id.recycleview_contacts)
    RecyclerView recycleviewContacts;
    @BindView(R.id.activity_contacts)
    LinearLayout activityContacts;
    @BindView(R.id.btn_contacts_binding)
    Button btnContactsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
        recycleviewContacts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleviewContacts.setAdapter(new ContactsRecyleViewAdapter(this, list));
        recycleviewContacts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        new BindPhoneDiaLog(this, R.style.CommonDialogStyle)
                .setOnBindPhoneDialogClickListener(new BindPhoneDiaLog.OnBindPhoneDiaLogClickListener() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onConfirmClick() {
                goToSubActivity();

            }
        }).show();
    }

    @OnClick({R.id.back, R.id.btn_contacts_binding})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_contacts_binding:
                goToSubActivity();
                break;
            default:
                break;
        }
    }

    private void goToSubActivity() {
        Intent intent = new Intent(this, BindingPhoneActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_contacts_binding)
    public void onViewClicked() {
    }
}
