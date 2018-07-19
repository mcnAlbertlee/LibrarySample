package com.example.albert.librarytest.arch;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;
import com.example.albert.librarytest.BaseActivity;
import com.example.albert.librarytest.R;

import io.fabric.sdk.android.Fabric;

/**
 * Library - android.arch.lifecycle
 * ViewModel + LiveData 사용
 */
public class ArchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_arch);

        setScreenTitle("Architecture Component");

        ListView listView = findViewById(R.id.list);

        ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        /* ModelView의 constructor에서 초기화 값이 필요 없을때 */
//        ArchActivityViewModel model = ViewModelProviders.of(this).get(ArchActivityViewModel.class);

        /* ModelView의 constructor에서 초기화 사용 - ViewModelProvider.Factory 사용 */
        ArchActivityViewModelWithFactory model = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new ArchActivityViewModelWithFactory("TEST");
            }
        }).get(ArchActivityViewModelWithFactory.class);

        // LiveData를 사용한 함수에 observe를 추가해서 데이터가 들어오면 Observer로 전달, 이후 UI를 업데이트
        model.getFruitList().observe(this, fruitlist -> {
            // update UI
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, fruitlist);
            // Assign adapter to ListView
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        });
    }

    public void testCrashlytics(View view) {
        throw new RuntimeException("this is a test crash");
    }
}
