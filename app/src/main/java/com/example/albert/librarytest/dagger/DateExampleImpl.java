package com.example.albert.librarytest.dagger;

import java.util.Date;

public class DateExampleImpl implements DateExample {
    private long createDate;

    public DateExampleImpl() {
        this.createDate = new Date().getTime();
    }

    @Override
    public long getCreateDate() {
        return createDate;
    }

    @Override
    public long getDate() {
        return new Date().getTime();
    }
}
