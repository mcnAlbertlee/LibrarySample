package com.example.albert.librarytest.arch.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserEntity")
    Flowable<List<UserEntity>> getAllUser();

    /**
     * Get the user from the table. Since for simplicity we only have one user in the database,
     * this query gets all users from the table, but limits the result to just the 1st user.
     *
     * @return the user from the table
     */
    @Query("SELECT * FROM UserEntity LIMIT 1")
    Flowable<UserEntity> getUser();

    /**
     * Insert a userEntity in the database. If the userEntity already exists, replace it.
     *
     * @param userEntity the userEntity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(UserEntity userEntity);

    /**
     * Delete all users.
     */
    @Query("DELETE FROM UserEntity")
    void deleteAllUsers();
}
