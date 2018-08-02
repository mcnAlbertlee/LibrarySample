package com.example.albert.librarytest.arch.room;

import io.reactivex.Flowable;

public class UserLocalDataSource implements UserDataSource {

    private final UserDao mUserDao;

    public UserLocalDataSource(UserDao userDao) {
        mUserDao = userDao;
    }

    @Override
    public Flowable<UserEntity> getUser() {
        return mUserDao.getUser();
    }

    @Override
    public void insertOrUpdateUser(UserEntity userEntity) {
        mUserDao.insertUsers(userEntity);
    }

    @Override
    public void deleteAllUsers() {
        mUserDao.deleteAllUsers();
    }
}
