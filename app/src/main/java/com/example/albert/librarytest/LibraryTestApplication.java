package com.example.albert.librarytest;

import android.text.TextUtils;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.crashlytics.android.Crashlytics;
import com.example.albert.librarytest.common.Constants;
import com.example.albert.librarytest.di.DaggerApplicationComponent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.fabric.sdk.android.Fabric;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * 1. DaggerApplication 세팅
 * 2. Fabric - Crashlytics 세팅
 */
public class LibraryTestApplication extends DaggerApplication {

    private List<Disposable> disposables;

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

        disposables = new ArrayList<>();
        /*
         Crashlytics 4. Initialize Crashlytics Kit

         # AndroidManifest.xml에 메타데이터로 fabric apikey 추가
          */
        Fabric.with(this, new Crashlytics());

        // Fast Android Networking Library
        AndroidNetworking.initialize(getApplicationContext());

        // rxjava 에러 공통처리.
        RxJavaPlugins.setErrorHandler(e -> {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            if(!isRelease()){
                Crashlytics.log(Log.ERROR, "RX_ERROR_LOG", sw.toString());
            }
            Crashlytics.logException(e);
        });
    }

    public boolean isRelease() {
        return TextUtils.equals(BuildConfig.BUILD_TYPE, Constants.BUILD_RELEASE);
    }


    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (Disposable disposable : disposables) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }

}
