package com.example.albert.librarytest.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.albert.librarytest.R;
import com.example.albert.librarytest.common.Constants;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;

/**
 * AsyncSubject 는 Observable이 방출되고 난 마지막 아이템만을 동일하게 보낸다.
 */
public class AsyncSubjectExampleActivity extends RxBaseActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenTitle(getResources().getString(R.string.bt_rx_async_subject));
    }

    public void startRxExample(View view) {
        tvRxResult.setText("");

        onStartLoading();

        AsyncSubject<Integer> source = AsyncSubject.create();

        source.subscribe(getFirstObserver());

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);

        source.subscribe(getSecondObserver());

        source.onNext(5);
        source.onNext(6);

        source.onComplete();
    }

    private Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                tvRxResult.append(" First onSubscribe : isDisposed :" + d.isDisposed());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " First onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                tvRxResult.append(" First onNext : value : " + value);
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " First onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tvRxResult.append(" First onError : " + e.getMessage());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " First onError : " + e.getMessage());

                onStopLoading();
            }

            @Override
            public void onComplete() {
                tvRxResult.append(" First onComplete");
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " First onComplete");

                onStopLoading();
            }
        };
    }

    private Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                tvRxResult.append(" Second onSubscribe : isDisposed :" + d.isDisposed());
                Log.d(TAG, " Second onSubscribe : " + d.isDisposed());
                tvRxResult.append(Constants.LINE_SEPARATOR);
            }

            @Override
            public void onNext(Integer value) {
                tvRxResult.append(" Second onNext : value : " + value);
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " Second onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tvRxResult.append(" Second onError : " + e.getMessage());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " Second onError : " + e.getMessage());

                onStopLoading();
            }

            @Override
            public void onComplete() {
                tvRxResult.append(" Second onComplete");
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " Second onComplete");

                onStopLoading();
            }
        };
    }
}
