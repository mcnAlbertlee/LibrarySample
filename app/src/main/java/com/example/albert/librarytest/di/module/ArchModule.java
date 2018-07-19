package com.example.albert.librarytest.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.example.albert.librarytest.arch.ArchActivity;
import com.example.albert.librarytest.arch.ArchActivityViewModelWithFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ArchModule {
    @Provides
    public ArchActivityViewModelWithFactory provideArchActivityViewModelWithFactory(ArchActivity archActivity) {
        return ViewModelProviders.of(archActivity, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new ArchActivityViewModelWithFactory("Test");
            }
        }).get(ArchActivityViewModelWithFactory.class);
    }
}
