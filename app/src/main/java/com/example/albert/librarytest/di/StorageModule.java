package com.example.albert.librarytest.di;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.albert.librarytest.arch.room.UserDao;
import com.example.albert.librarytest.arch.room.UserDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @NonNull
    @Singleton
    public UserDatabase provideUserDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                UserDatabase.class, "user.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(UserDatabase userDatabase) {
        return userDatabase.userDao();
    }
}
