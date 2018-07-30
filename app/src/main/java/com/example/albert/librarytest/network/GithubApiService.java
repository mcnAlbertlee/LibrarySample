package com.example.albert.librarytest.network;

import com.example.albert.librarytest.network.dto.UsersResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApiService {

    @GET("/users/{user}")
    Single<UsersResponse> getuserInfo(@Path("user")String userName);
}
