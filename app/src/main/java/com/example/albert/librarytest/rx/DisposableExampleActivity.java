package com.example.albert.librarytest.rx;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.example.albert.librarytest.common.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DisposableExampleActivity extends RxBaseActivity {

    private String TAG = this.getClass().getSimpleName();

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // do not send event after activity has been destroyed
        disposable.clear();
    }

    public void startRxExample(View view) {
        tvRxResult.setText("");

        onStartLoading();

        disposable.add(
                sampleObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver())
        );
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(() -> {
            SystemClock.sleep(2000);
            return Observable.just("one", "two", "three", "four", "five" );
        });
    }

//    static Observable<String> sampleObservable() {
//        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
//            @Override
//            public ObservableSource<? extends String> call() throws Exception {
//                // Do some long running operation
//                SystemClock.sleep(2000);
//                return Observable.just("one", "two", "three", "four", "five");
//            }
//        });
//    }

    private DisposableObserver<String> getObserver() {
        return new DisposableObserver<String>() {
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
