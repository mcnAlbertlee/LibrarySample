package com.example.albert.librarytest.rx;

import android.util.Log;
import android.view.View;

import com.example.albert.librarytest.common.Constants;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * http://rxmarbles.com/#take
 */
public class TakeExampleActivity extends RxBaseActivity {

    private String TAG = this.getClass().getSimpleName();

    public void startRxExample(View view) {
        tvRxResult.setText("");

        onStartLoading();

        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(3)
                .subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.just(1, 2, 3, 4, 5);
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                tvRxResult.append(" onSubscribe isDisposed : " + d.isDisposed());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onSubscribe isDisposed : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
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
