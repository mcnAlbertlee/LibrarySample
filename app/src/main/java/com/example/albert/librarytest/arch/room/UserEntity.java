package com.example.albert.librarytest.arch.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity
public class UserEntity {

    @NonNull
    @PrimaryKey
    private String userid;

    private String username;

    public UserEntity(){}

    @Ignore
    public UserEntity(String userName) {
        userid = UUID.randomUUID().toString();
        username = userName;
    }

    @Ignore
    public UserEntity(String mId, String userName) {
        this.userid = mId;
        this.username = userName;
    }

    @NonNull
    public String getUserid() {
        return userid;
    }

    public void setUserid(@NonNull String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
