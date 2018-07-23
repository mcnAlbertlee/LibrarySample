package com.example.albert.librarytest.dagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.albert.librarytest.BaseActivity;
import com.example.albert.librarytest.R;

import javax.inject.Inject;

public class DaggerDateActivity extends BaseActivity {

    @Inject
    DaggerDateViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dagger_date);
        setScreenTitle("Dagger Date Sample");

        TextView tvCreateDateText = findViewById(R.id.tvCreateDate);
        TextView tvDateText = findViewById(R.id.tvDate);

        viewModel.getCurrentDate();
        viewModel.getDate();

        viewModel.createDateText.observe(this, dateText -> tvCreateDateText.setText(dateText));
        viewModel.dateText.observe(this, dateText -> tvDateText.setText(dateText));
    }
}
