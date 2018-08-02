package com.example.albert.librarytest.di;

import com.example.albert.librarytest.arch.ArchActivity;
import com.example.albert.librarytest.arch.room.RoomActivity;
import com.example.albert.librarytest.dagger.DaggerDateActivity;
import com.example.albert.librarytest.di.module.ArchModule;
import com.example.albert.librarytest.di.module.DaggerDateModule;
import com.example.albert.librarytest.di.module.MainModule;
import com.example.albert.librarytest.di.module.RoomModule;
import com.example.albert.librarytest.di.module.RxModule;
import com.example.albert.librarytest.main.MainActivity;
import com.example.albert.librarytest.rx.RxActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = {ArchModule.class})
    abstract ArchActivity archActivity();

    @ContributesAndroidInjector(modules = {DaggerDateModule.class})
    abstract DaggerDateActivity daggerDateActivity();

    @ContributesAndroidInjector(modules = RxModule.class)
    abstract RxActivity rxActivity();

    @ContributesAndroidInjector(modules = RoomModule.class)
    abstract RoomActivity roomActivity();
}
