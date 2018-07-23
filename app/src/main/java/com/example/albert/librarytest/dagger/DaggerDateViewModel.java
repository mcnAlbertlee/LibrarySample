package com.example.albert.librarytest.dagger;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Date;

public class DaggerDateViewModel extends ViewModel {

    DateExampleImpl dateExample;

    public MutableLiveData<String> createDateText;
    public MutableLiveData<String> dateText;

    public DaggerDateViewModel(DateExampleImpl dateExample) {
        this.dateExample = dateExample;
    }

    public void getCurrentDate() {
        if(createDateText == null) {
            createDateText = new MutableLiveData<>();
        }

        createDateText.setValue((new Date(dateExample.getCreateDate())).toString());
    }

    public void getDate() {
        if(dateText == null) {
            dateText = new MutableLiveData<>();
        }

        dateText.setValue((new Date(dateExample.getDate())).toString());
    }
}
