package com.example.albert.librarytest.di;

import com.example.albert.librarytest.arch.ArchActivity;
import com.example.albert.librarytest.di.module.ArchModule;
import com.example.albert.librarytest.di.module.MainModule;
import com.example.albert.librarytest.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = {ArchModule.class})
    abstract ArchActivity archActivity();
}
