package com.example.albert.librarytest.arch.room;

import io.reactivex.Flowable;

public interface UserDataSource {

    /**
     * Gets the user from the data source.
     *
     * @return the user from the data source.
     */
    Flowable<UserEntity> getUser();

    /**
     * Inserts the userEntity into the data source, or, if this is an existing userEntity, updates it.
     *
     * @param userEntity the userEntity to be inserted or updated.
     */
    void insertOrUpdateUser(UserEntity userEntity);

    /**
     * Deletes all users from the data source.
     */
    void deleteAllUsers();
}
