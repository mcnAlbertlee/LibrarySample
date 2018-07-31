package com.example.albert.librarytest.rx;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

public class RxFreeTest {

    private final static String TAG = "RxFreeTest";
    private static MutableLiveData<Boolean> mStatus = new MutableLiveData<>();
    public static Maybe<Boolean> getBooleanStatus() {
        return Maybe.create(e -> e.onSuccess(true));
    }

    public static void hasStatus() {
        getBooleanStatus()
                .subscribe(
                        status -> mStatus.setValue(status),
                        throwable -> throwable.printStackTrace()
                );
    }


    public static void loadAssets() {
        Completable.create(emitter -> {
            Thread.sleep(2000);
            emitter.onComplete();
        }).subscribeOn(Schedulers.computation())
                .subscribe(
                        () -> Crashlytics.log(Log.DEBUG, TAG, "load assets completed!"),
                        throwable -> throwable.printStackTrace()
                );

    }
}
