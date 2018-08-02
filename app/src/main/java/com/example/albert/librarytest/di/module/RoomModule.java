package com.example.albert.librarytest.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.example.albert.librarytest.arch.room.RoomActivity;
import com.example.albert.librarytest.arch.room.RoomUserAdapter;
import com.example.albert.librarytest.arch.room.RoomViewModel;
import com.example.albert.librarytest.arch.room.UserDao;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Provides
    public RoomUserAdapter provideRoomUserAdapter() {
        return new RoomUserAdapter();
    }

    @Provides
    public RoomViewModel provideRoomViewModel(RoomActivity roomActivity, UserDao userDao) {
        return ViewModelProviders.of(roomActivity, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new RoomViewModel(userDao);
            }
        }).get(RoomViewModel.class);
    }

}
