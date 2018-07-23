package com.example.albert.librarytest.dagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.albert.librarytest.BaseActivity;
import com.example.albert.librarytest.R;

import javax.inject.Inject;

public class DaggerDateActivity extends BaseActivity {

    @Inject
    public DaggerDateViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dagger_date);
        setScreenTitle("Dagger Date Sample");

        TextView tvCreateDateText = findViewById(R.id.tvCreateDate);
        TextView tvDateText = findViewById(R.id.tvDate);

        viewModel.loadCreateDate();
        viewModel.loadDate();

        viewModel.getCreateDate().observe(this, dateText -> tvCreateDateText.setText(dateText));
        viewModel.getDate().observe(this, dateText -> tvDateText.setText(dateText));
    }
}
