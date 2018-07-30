package com.example.albert.librarytest.di;

import com.example.albert.librarytest.BuildConfig;
import com.example.albert.librarytest.network.GithubApiService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public GithubApiService provideGithubApiClient(@Named("GITHUB_RETROFIT_API") Retrofit retrofitGithubApi) {
        return retrofitGithubApi.create(GithubApiService.class);
    }

    @Provides
    @Singleton
    @Named("GITHUB_RETROFIT_API")
    public Retrofit retrofitGithubApi() {
        String url = BuildConfig.GITHUBAPI_URL;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .client(client)
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();

        return retrofit;
    }
}
