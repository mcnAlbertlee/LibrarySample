package com.example.albert.librarytest.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.example.albert.librarytest.network.GithubApiService;
import com.example.albert.librarytest.rx.RxActivity;
import com.example.albert.librarytest.rx.RxViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {
//    @Provides
//    public RxSearchAdapter provideRxSearchAdapter() {
//        return new RxSearchAdapter();
//    }

    @Provides
    public RxViewModel provideRxViewModel(RxActivity rxActivity, GithubApiService githubApiService) {


        return ViewModelProviders.of(rxActivity, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new RxViewModel(githubApiService);
            }
        }).get(RxViewModel.class);
    }

}
