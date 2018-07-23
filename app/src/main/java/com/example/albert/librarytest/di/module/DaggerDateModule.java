package com.example.albert.librarytest.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.example.albert.librarytest.dagger.DaggerDateActivity;
import com.example.albert.librarytest.dagger.DaggerDateViewModel;
import com.example.albert.librarytest.dagger.DateExampleImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DaggerDateModule {
    @Provides
    public DaggerDateViewModel provideDaggerDateViewModel(DaggerDateActivity daggerDateActivity, DateExampleImpl dateExample) {
        return ViewModelProviders.of(daggerDateActivity, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new DaggerDateViewModel(dateExample);
            }
        }).get(DaggerDateViewModel.class);
    }
}
