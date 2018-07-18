package com.example.albert.librarytest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivityViewModelWithFactory extends ViewModel {
    private MutableLiveData<List<String>> fruitLists;
    private String addText;

    public MainActivityViewModelWithFactory(String addText) {
        this.addText = addText;
    }

    LiveData<List<String>> getFruitList() {
        if(fruitLists == null) {
            fruitLists = new MutableLiveData<>();

            loadFruits();
        }

        return fruitLists;
    }

    private void loadFruits() {
        new Handler().postDelayed(() -> {
                    List<String> fruitsStringList = new ArrayList<>();
                    fruitsStringList.add(addText);
                    fruitsStringList.add("Mango");
                    fruitsStringList.add("Apple");
                    fruitsStringList.add("Orange");
                    fruitsStringList.add("Banana");
                    fruitsStringList.add("Grapes");
                    long seed = System.nanoTime();
                    Collections.shuffle(fruitsStringList, new Random(seed));

                    fruitLists.setValue(fruitsStringList);
                }, 5000);
    }
}
