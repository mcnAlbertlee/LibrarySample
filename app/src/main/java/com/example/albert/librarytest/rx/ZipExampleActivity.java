package com.example.albert.librarytest.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.albert.librarytest.R;
import com.example.albert.librarytest.common.Constants;
import com.example.albert.librarytest.common.Utils;
import com.example.albert.librarytest.rx.dao.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * http://rxmarbles.com/#zip
 */
public class ZipExampleActivity extends RxBaseActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenTitle(getResources().getString(R.string.bt_rx_zip));
    }

    public void startRxExample(View view) {
        tvRxResult.setText("");

        onStartLoading();

        Observable.zip(getCricketFansObservable(), getFootballFansObservable(),
                (users, users2) -> Utils.filterUserWhoLovesBoth(users, users2))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());

    }

    private Observable<List<User>> getCricketFansObservable() {
        return Observable.create(e -> {
            if(!e.isDisposed()) {
                e.onNext(Utils.getUserListWhoLovesCricket());
                e.onComplete();
            }
        });
    }

    private Observable<List<User>> getFootballFansObservable() {
        return Observable.create(e -> {
            if(!e.isDisposed()) {
                e.onNext(Utils.getUserListWhoLovesFootball());
                e.onComplete();
            }
        });
    }

//    private Observable<List<UserEntity>> getCricketFansObservable() {
//        return Observable.create(new ObservableOnSubscribe<List<UserEntity>>() {
//            @Override
//            public void subscribe(ObservableEmitter<List<UserEntity>> e) throws Exception {
//                if (!e.isDisposed()) {
//                    e.onNext(Utils.getUserListWhoLovesCricket());
//                    e.onComplete();
//                }
//            }
//        });
//    }
//
//    private Observable<List<UserEntity>> getFootballFansObservable() {
//        return Observable.create(new ObservableOnSubscribe<List<UserEntity>>() {
//            @Override
//            public void subscribe(ObservableEmitter<List<UserEntity>> e) throws Exception {
//                if (!e.isDisposed()) {
//                    e.onNext(Utils.getUserListWhoLovesFootball());
//                    e.onComplete();
//                }
//            }
//        });
//    }


    private Observer<List<User>> getObserver() {
        return new Observer<List<User>>() {

            @Override
            public void onSubscribe(Disposable d) {
                tvRxResult.append(" onSubscribe isDisposed : " + d.isDisposed());
                tvRxResult.append(Constants.LINE_SEPARATOR);
                Log.d(TAG, " onSubscribe isDisposed : " + d.isDisposed());
            }

            @Override
            public void onNext(List<User> userList) {
                tvRxResult.append(" onNext");
                tvRxResult.append(Constants.LINE_SEPARATOR);
                for (User user : userList) {
                    tvRxResult.append(" firstname : " + user.firstname);
                    tvRxResult.append(Constants.LINE_SEPARATOR);
                }
                Log.d(TAG, " onNext : " + userList.size());
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
