package com.example.albert.librarytest.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.albert.librarytest.R;
import com.example.albert.librarytest.common.Constants;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * http://rxmarbles.com/#merge
 *
 * 2개의 옵저버블의 흐름의 순서에 따라 자연스럽게 합치기
 */
public class MergeExampleActivity extends RxBaseActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenTitle(getResources().getString(R.string.bt_rx_merge));
    }

    public void startRxExample(View view) {
        tvRxResult.setText("");

        onStartLoading();

        /*
         * Using merge operator to combine Observable : merge does not maintain
         * the order of Observable.
         * It will emit all the 7 values may not be in order
         * Ex - "A1", "B1", "A2", "A3", "A4", "B2", "B3" - may be anything
         */

        // Todo 위 처럼 예상했으나 concat와 동일하게 a 이후 b 출력, 왜그럴까요..

        final Observable<String> aObservable = getObservableA();
        final Observable<String> bObservable = getObservableB();

        Observable
                // merge는 두 옵저버블의 순서를 그대로 반영해서 합치는 오퍼레이터
                .merge(aObservable, bObservable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    public Observable<String> getObservableA() {
        final String[] aStrings = { "A1", "A2", "A3", "A4" };
        return Observable.fromArray(aStrings);
    }

    public Observable<String> getObservableB() {
        final String[] bStrings = { "B1", "B2", "B3" };
        return Observable.fromArray(bStrings);
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                tvRxResult.append(" onSubscribe isDisposed : " + d.isDisposed());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onSubscribe isDisposed : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                tvRxResult.append(" onNext : value : " + value);
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tvRxResult.append(" onError : " + e.getMessage());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());

                onStopLoading();
            }

            @Override
            public void onComplete() {
                tvRxResult.append(" onComplete");
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");

                onStopLoading();
            }
        };
    }
}
