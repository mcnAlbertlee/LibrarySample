package com.example.albert.librarytest.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Dagger - Context를 바인드 시켜줄 추상 메소드 작성
 */
@Module
public abstract class ApplicationModule {
    // 주입 가능한 context로 어플리케이션에 보여준다.
    @Binds
    abstract Context bindContext(Application application);
}
