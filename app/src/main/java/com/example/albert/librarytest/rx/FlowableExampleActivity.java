package com.example.albert.librarytest.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.albert.librarytest.R;
import com.example.albert.librarytest.common.Constants;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Flowable vs Observable 차이 - https://01010011.blog/2017/03/29/rxjava-flowable-%EA%B3%BC-observable-%EC%9D%98-%EC%B0%A8%EC%9D%B4/
 *
 * Observable 을 backpressure buffer 생성 없이 사용하면 OutOfMemoryException이 발생
 * Flowable 을 사용하면 default buffer size(128) 이상 backpressure buffer 에 element 가 쌓일 경우 흐름제어를 한다.
 *
 * reduce - http://rxmarbles.com/#reduce
 */
public class FlowableExampleActivity extends RxBaseActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenTitle(getResources().getString(R.string.bt_rx_flowable));
    }

    public void startRxExample(View view) {
        tvRxResult.setText("");

        onStartLoading();

        getFlowable()
                // reduce 50 부터 시작해서 하나씩 더해서 하나의 값으로 줄인다.
                .reduce(50, (t1, t2) -> t1 + t2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
//
//        Flowable<Integer> observable = Flowable.just(1, 2, 3, 4);
//
//        observable.reduce(50, new BiFunction<Integer, Integer, Integer>() {
//            @Override
//            public Integer apply(Integer t1, Integer t2) {
//                return t1 + t2;
//            }
//        }).subscribe(getObserver());
//

    }

    private Flowable<Integer> getFlowable() {
        return Flowable.just(1, 2, 3, 4, 5);
    }

    private SingleObserver<Integer> getObserver() {
        return new SingleObserver<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                tvRxResult.append(" onSubscribe isDisposed : " + d.isDisposed());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onSubscribe isDisposed : " + d.isDisposed());
            }

            @Override
            public void onSuccess(Integer value) {
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
