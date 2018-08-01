package com.example.albert.librarytest.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.albert.librarytest.R;
import com.example.albert.librarytest.common.Constants;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * http://rxmarbles.com/#last
 *
 * 마지막 아이템 가져오기
 */
public class LastOperatorExampleActivity extends RxBaseActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenTitle(getResources().getString(R.string.bt_rx_last_operator));
    }

    public void startRxExample(View view) {
        tvRxResult.setText("");

        onStartLoading();

        getObservable()
                // return Observable.empty() 을 하면 값이 없으므로 기본으로 설정한 "A1"이 전달된다. Single 사용
                .last("A1")
                .subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("A1", "A2", "A3", "A4", "A5", "A6");
//        return Observable.empty();
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
                tvRxResult.append(" onNext : value : " + value);
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onNext : value : " + value);

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
