package com.why.livelife.View;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.why.livelife.R;
import com.why.livelife.Utils.DensityUtils;

/**
 * Created by lenovo on 2017/5/7.
 */

public class BindPhoneDiaLog extends Dialog implements View.OnClickListener {

    private TextView title;
    private TextView desc;
    private Button cancel;
    private Button confirm;
    private OnBindPhoneDiaLogClickListener onBindPhoneDialogClickListener;

    public BindPhoneDiaLog(Context context) {
        super(context);
        init();
    }


    public BindPhoneDiaLog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected BindPhoneDiaLog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.bind_phone_dialog, null);
        title = (TextView) inflate.findViewById(R.id.tv_dialog_title);
        desc = (TextView) inflate.findViewById(R.id.tv_dialog_desc);
        cancel = (Button) inflate.findViewById(R.id.btn_dialog_cancel);
        confirm = (Button) inflate.findViewById(R.id.btn_dialog_confirm);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
        setContentView(inflate);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = (int) (DensityUtils.getScreenWidth(getContext())*0.8);
    }

    public BindPhoneDiaLog setTitle(String title) {
        this.title.setText(title);
        return this;
    }

    public BindPhoneDiaLog setDesc(String desc) {
        this.desc.setText(desc);
        return this;
    }

    public BindPhoneDiaLog setCancel(String cancel) {
        this.cancel.setText(cancel);
        return this;
    }

    public BindPhoneDiaLog setConfirm(String confirm) {
        this.confirm.setText(confirm);
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_cancel:
                if (onBindPhoneDialogClickListener != null) {
                    onBindPhoneDialogClickListener.onCancelClick();
                }
                break;
            case R.id.btn_dialog_confirm:
                if (onBindPhoneDialogClickListener != null) {
                    onBindPhoneDialogClickListener.onConfirmClick();
                }
                break;
            default:
                break;
        }
        dismiss();

    }

    public interface OnBindPhoneDiaLogClickListener {
        void onCancelClick();

        void onConfirmClick();
    }

    public BindPhoneDiaLog setOnBindPhoneDialogClickListener(OnBindPhoneDiaLogClickListener onBindPhoneDialogClickListener) {
        this.onBindPhoneDialogClickListener = onBindPhoneDialogClickListener;
        return this;
    }

}
