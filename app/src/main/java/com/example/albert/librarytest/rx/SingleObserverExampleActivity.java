package com.example.albert.librarytest.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.albert.librarytest.R;
import com.example.albert.librarytest.common.Constants;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 이름 그대로 한번에 하나만 보낼수 있다.
 * List를 보내고 싶으면 List를 통째로 넣고 통째로 받는다.
 */
public class SingleObserverExampleActivity extends RxBaseActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenTitle(getResources().getString(R.string.bt_rx_single_observer));
    }

    public void startRxExample(View view) {
        tvRxResult.setText("");

        onStartLoading();

        Single.just("singleTest")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());

        Single.just(Arrays.asList("one", "two", "three"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getListObserver());
    }

    private SingleObserver<String> getObserver() {
        return new SingleObserver<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                tvRxResult.append(" onSubscribe isDisposed : " + d.isDisposed());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onSubscribe isDisposed : " + d.isDisposed());
            }

            @Override
            public void onSuccess(String value) {
                tvRxResult.append(" onSuccess : value : " + value);
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onSuccess : value : " + value);

                onStopLoading();
            }

            @Override
            public void onError(Throwable e) {
                tvRxResult.append(" onError : " + e.getMessage());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());

                onStopLoading();
            }
        };
    }

    private SingleObserver<List<String>> getListObserver() {
        return new SingleObserver<List<String>>() {

            @Override
            public void onSubscribe(Disposable d) {
                tvRxResult.append(" onSubscribe isDisposed : " + d.isDisposed());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onSubscribe isDisposed : " + d.isDisposed());
            }

            @Override
            public void onSuccess(List<String> value) {
                tvRxResult.append(" onSuccess : value : " + value);
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onSuccess : value : " + value);

                onStopLoading();
            }

            @Override
            public void onError(Throwable e) {
                tvRxResult.append(" onError : " + e.getMessage());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());

                onStopLoading();
            }
        };
    }
}
