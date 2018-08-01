package com.example.albert.librarytest.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.albert.librarytest.R;

public class RxBaseActivity extends AppCompatActivity {
    TextView tvRxResult;
    ProgressBar pbLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rx_example);

        pbLoading = findViewById(R.id.pbLoading);
        tvRxResult = findViewById(R.id.tvRxResult);
    }

    protected void onStartLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    protected void onStopLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    protected void setScreenTitle(String title) {
        setTitle(title);
    }
}
