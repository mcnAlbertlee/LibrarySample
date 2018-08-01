package com.example.albert.librarytest.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.albert.librarytest.R;
import com.example.albert.librarytest.common.Constants;
import com.example.albert.librarytest.common.Utils;
import com.example.albert.librarytest.rx.dao.ApiUser;
import com.example.albert.librarytest.rx.dao.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * http://rxmarbles.com/#map
 */
public class MapExampleActivity extends RxBaseActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenTitle(getResources().getString(R.string.bt_rx_map));
    }

    public void startRxExample(View view) {
        tvRxResult.setText("");

        onStartLoading();

        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(apiusers -> Utils.convertApiUserListToUserList(apiusers))
                .subscribe(getObserver());
    }

    private Observable<List<ApiUser>> getObservable() {
        return Observable.create(e -> {
            if(!e.isDisposed()) {
                e.onNext(Utils.getApiUserList());
                e.onComplete();
            }
        });
    }

//    private Observable<List<ApiUser>> getObservable() {
//        return Observable.create(new ObservableOnSubscribe<List<ApiUser>>() {
//            @Override
//            public void subscribe(ObservableEmitter<List<ApiUser>> e) throws Exception {
//                if (!e.isDisposed()) {
//                    e.onNext(Utils.getApiUserList());
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
