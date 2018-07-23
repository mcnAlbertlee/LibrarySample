package com.example.albert.librarytest.di;

import android.content.Context;
import android.content.res.Resources;

import com.example.albert.librarytest.dagger.DateExampleImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CoreModule {
    @Provides
    Resources provideResorces(Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    public DateExampleImpl provideDateExampleImpl() {
        return new DateExampleImpl();
    }
}
