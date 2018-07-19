package com.example.albert.librarytest.di.module;

import android.arch.lifecycle.ViewModelProviders;

import com.example.albert.librarytest.main.MainActivity;
import com.example.albert.librarytest.main.MainListAdapter;
import com.example.albert.librarytest.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    public MainListAdapter provideMainAdapter(MainViewModel viewModel) {
        return new MainListAdapter(viewModel);
    }

    @Provides
    public MainViewModel provideMainViewModel(MainActivity mainActivity) {
        return ViewModelProviders.of(mainActivity).get(MainViewModel.class);
    }
}
