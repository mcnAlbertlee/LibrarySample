package com.example.albert.librarytest.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.Handler;

import com.example.albert.librarytest.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<String>> sampleList;

    LiveData<List<String>> getSampleList(Context context) {
        if(sampleList == null) {
            sampleList = new MutableLiveData<>();

            loadTitle(context);
        }

        return sampleList;
    }

    private void loadTitle(Context context) {
        new Handler().postDelayed(() -> {
                    List<String> fruitsStringList = new ArrayList<>();
                    fruitsStringList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.sample_list)));
                    sampleList.setValue(fruitsStringList);
                }, 1000);
    }
}
