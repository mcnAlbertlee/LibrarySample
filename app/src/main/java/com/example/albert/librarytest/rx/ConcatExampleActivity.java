package com.example.albert.librarytest.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.albert.librarytest.R;
import com.example.albert.librarytest.common.Constants;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * http://rxmarbles.com/#concat
 *
 * 2개의 옵저버블을 합쳐서 1개의 스트림으로 변환(첫번째 옵저버블 먼저나오고 두번째 옵저버블이 나옴)
 */
public class ConcatExampleActivity extends RxBaseActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenTitle(getResources().getString(R.string.bt_rx_concat));
    }

    public void startRxExample(View view) {
        tvRxResult.setText("");

        onStartLoading();

        Observable
                // concat 를 사용해서 2개의 옵저버블 결합
                .concat(getObservableA(), getObservableB())
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
