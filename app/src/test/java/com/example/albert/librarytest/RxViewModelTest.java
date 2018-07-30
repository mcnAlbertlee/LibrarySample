package com.example.albert.librarytest;

import com.example.albert.librarytest.network.GithubApiService;
import com.example.albert.librarytest.rx.RxViewModel;

import org.junit.BeforeClass;
import org.junit.Test;

import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

public class RxViewModelTest {
    @BeforeClass
    public static void beforeClass() {
        // rxjava의 io 스케쥴러를 메인쓰레드에서 돌게 한다. 유닛테스트에서 비동기 문제 해결.
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void test() {
        GithubApiService githubApiService = mock(GithubApiService.class);

        RxViewModel rxViewModel = new RxViewModel(githubApiService);

    }
}
