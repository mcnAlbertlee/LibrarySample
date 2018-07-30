package com.example.albert.librarytest.rx;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.albert.librarytest.network.GithubApiService;
import com.example.albert.librarytest.network.dto.UsersResponse;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class RxViewModel extends ViewModel {
    private final GithubApiService githubApiService;

    // Using RxRelay's implementation of publish subject - https://github.com/JakeWharton/RxRelay
    private PublishRelay autoCompletePublishSubject = PublishRelay.create();


    private String userName;

    private MutableLiveData<UsersResponse> userInfo = new MutableLiveData<>();

    public RxViewModel(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    public MutableLiveData<UsersResponse> getLiveUserInfo() {
        return userInfo;
    }

    public void enterUserName(String userName) {
        this.userName = userName;
    }

    public void getUserInfo() {
        githubApiService.getuserInfo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( usersResponse -> {
                    Log.d("RxViewModel", "usersResponse: " + usersResponse.toString());

                    userInfo.setValue(usersResponse);
                }, throwable -> {
                    try {
                        if (throwable instanceof HttpException) {
                            HttpException httpException = (HttpException) throwable;
                            String errBody = httpException.response().errorBody().string();

                            Log.d("getUserInfo", "errBody: " + errBody);

                        } else {
                            throwable.printStackTrace();
                        }

                        userInfo.setValue(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * Called only once when the `ViewModel` is being created
     * Initialises the autocomplete publish subject
     */
    private void configureAutoComplete() {
        autoCompletePublishSubject
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap((Function) githubApiService.getuserInfo(userName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResponse -> userInfo.setValue((UsersResponse) userResponse));
    }

    /**
     * Called on every character change made to the search `EditText`
     */
    public void onOmnibarInputStateChanged(String query) {
        autoCompletePublishSubject.accept(query.trim());
    }
}
