package com.example.albert.librarytest.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.albert.librarytest.BaseActivity;
import com.example.albert.librarytest.R;

import javax.inject.Inject;


public class MainActivity extends BaseActivity {

    @Inject
    MainListAdapter mainListAdapter;

    @Inject
    MainViewModel mainViewModel;

    private RecyclerView rvMain;
    private ProgressBar pbMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setScreenTitle("SampleList");

        rvMain = findViewById(R.id.rvMain);
        pbMain = findViewById(R.id.pbMain);
        pbMain.setVisibility(View.VISIBLE);

        // 리사이클뷰 초기화
        setRecyclerView();

        // LiveData 사용해서 리스트 데이터를 가져오고 옵저버에서 받아올때 리스트 갱신
        mainViewModel.getSampleList(MainActivity.this).observe(this, sampleList -> {
            mainListAdapter.addAll(sampleList);

            pbMain.setVisibility(View.GONE);
        });
    }

    public void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMain.setLayoutManager(layoutManager);
        rvMain.setAdapter(mainListAdapter);
    }
}
