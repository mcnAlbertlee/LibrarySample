package com.example.albert.librarytest;

import com.crashlytics.android.Crashlytics;
import com.example.albert.librarytest.di.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.fabric.sdk.android.Fabric;

/**
 * 1. DaggerApplication 세팅
 * 2. Fabric - Crashlytics 세팅
 */
public class LibraryTestApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        /*
        DaggerApplicationComponent 를 사용하기 위해 Gradle 빌드 한번 진행
         */
        return DaggerApplicationComponent.builder()
                .application(this)
                .create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /*
         Crashlytics 4. Initialize Crashlytics Kit

         # AndroidManifest.xml에 메타데이터로 fabric apikey 추가
         <meta-data
            android:name="io.fabric.ApiKey"
            android:value="50324704e3809ba7f3262d55140aa77103f2ba2d"
            />
          */
        Fabric.with(this, new Crashlytics());
    }

}
