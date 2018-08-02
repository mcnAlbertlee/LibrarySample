package com.example.albert.librarytest.arch.room;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RoomViewModel extends ViewModel {

    private MutableLiveData<List<UserEntity>> userList = new MutableLiveData<>();
    private MutableLiveData<Boolean> insertStatus = new MutableLiveData<>();
    private MutableLiveData<Boolean> deleteStatus = new MutableLiveData<>();

    UserDao userDao;

    public RoomViewModel(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUserList() {
        userDao.getAllUser()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(users -> userList.postValue(users));
    }

    public void insertUser(UserEntity userEntity) {
        io.reactivex.Observable.just(userDao)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(dao -> {
                    dao.insertUsers(userEntity);
                    insertStatus.postValue(true);

                    getUserList();

                });
//        insertStatus.postValue(userDao.insertUsers(userEntity));
    }

    public void deleteAllUsers() {
        io.reactivex.Observable.just(userDao)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(dao -> {
                    userDao.deleteAllUsers();
                    deleteStatus.postValue(true);

                    getUserList();
                });

//        deleteStatus.postValue(userDao.deleteAllUsers());
    }

    public MutableLiveData<List<UserEntity>> getLivedataUserList() {
        return userList;
    }

    public MutableLiveData<Boolean> getLivedataInsertStatus() {
        return insertStatus;
    }
    public MutableLiveData<Boolean> getLivedataDeleteStatus() {
        return deleteStatus;
    }

}
