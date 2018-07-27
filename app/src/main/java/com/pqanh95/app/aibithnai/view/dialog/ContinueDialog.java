package com.pqanh95.app.aibithnai.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.pqanh95.app.aibithnai.R;
import com.pqanh95.app.aibithnai.view.activity.MainActivity;

public class ContinueDialog extends Dialog implements View.OnClickListener {
    private ImageView imgContinue;
    private ImageView imgExit;
    private OnClickButtonDialog onClickButtonDialog;

    public void setOnClickButtonDialog(OnClickButtonDialog onClickButtonDialog) {
        this.onClickButtonDialog = onClickButtonDialog;
    }

    private void initializeComponents() {
        imgContinue = findViewById(R.id.imgContinue);
        imgExit = findViewById(R.id.imgExit);
        imgContinue.setOnClickListener(this);
        imgExit.setOnClickListener(this);
    }

    public ContinueDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quaman);
        initializeComponents();

    }

    @Override
    protected void onStart() {
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().getAttributes().windowAnimations = R.style.DialogStyle;
//        setCancelable(false);
        super.onStart();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgContinue:
//                Toast.makeText(getContext(), "Nhấn nút Continue", Toast.LENGTH_SHORT).show();
                onClickButtonDialog.onClickButtonDialog();
                dismiss();
                break;
            case R.id.imgExit:
                Toast.makeText(getContext(), "Nhấn nút Exit", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public interface OnClickButtonDialog {
        void onClickButtonDialog();
    }
}
