package com.example.albert.librarytest.di;

import android.app.Application;

import com.example.albert.librarytest.LibraryTestApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Dagger 1. @Singleton, @Component(module 세팅 - Required: AndroidSupportInjectionModule.class) 사용
 * Dagger 2. AndroidInjector 상속 받기
 * Dagger 3. 빌더 패턴으로 구현 @Component.Builder 사용 - AndroidInjector.Builder를 상속받아서 구현
 * Dagger 4. Builder 추상 클래스안에서 @BindsInstance 로 인스턴스를 바인딩
  */
@Singleton
@Component(modules = {
        ActivityBindingModule.class,
        ApplicationModule.class,
        AndroidSupportInjectionModule.class
})
public interface ApplicationComponent extends AndroidInjector<LibraryTestApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<LibraryTestApplication> {
        public abstract @BindsInstance
        Builder application(Application application);

        public abstract ApplicationComponent build();
    }
}
