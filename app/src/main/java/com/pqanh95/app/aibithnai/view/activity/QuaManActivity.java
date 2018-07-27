package com.pqanh95.app.aibithnai.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.pqanh95.app.aibithnai.R;
import com.pqanh95.app.aibithnai.constant.Key;
import com.pqanh95.app.aibithnai.manager.GameManager;

public class QuaManActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgContinue;
    private ImageView imgExit;
    private TextView txtResult;
    private int level;

    private void initializerComponents() {
        imgContinue = findViewById(R.id.imgContinue);
        imgExit = findViewById(R.id.imgExit);
        txtResult = findViewById(R.id.txtAnswer);
    }

    private void initOnClickListener() {
        imgContinue.setOnClickListener(this);
        imgExit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quaman);

        initializerComponents();
        initOnClickListener();

        txtResult.setText(getIntent().getStringExtra(Key.RESULT));
        level = getIntent().getIntExtra(Key.LEVEL, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgContinue:
                Intent data = new Intent();
                data.putExtra(Key.KEY_CONTINUE, level + 1);
                setResult(Activity.RESULT_OK, data);
                finish();
                break;
            case R.id.imgExit:
                break;
        }
    }
}
