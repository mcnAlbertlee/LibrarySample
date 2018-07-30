package com.example.albert.librarytest;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTest {

    @BeforeClass
    public static void beforeClass() {
        // rxjava의 io 스케쥴러를 메인쓰레드에서 돌게 한다. 유닛테스트에서 비동기 문제 해결.
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void justTest() {
        Observable.just(1)
                .test()
                .assertSubscribed()
                .assertValues(1)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void assertResultTest() {
        Observable.just(1)
                .test()
                .assertResult(1)
                .assertNoErrors();
    }

    @Test
    public void completeableMaybeSingleTest() {
        Completable.complete()
                .test()
                .assertResult();

        Maybe.empty()
                .test()
                .assertResult();

        Maybe.just(1)
                .test()
                .assertResult(1);

        Single.just(1)
                .test()
                .assertResult(1);
    }

    @Test
    public void createTest() {
        Observable.create(emitter ->
                emitter.onNext(1))
                .test()
                .assertValuesOnly(1);
    }

    @Test
    public void neverTest() {
        Observable.never()
                .test()
                .assertEmpty();
    }

    @Test
    public void valueAtTest() {
        Observable.just(1, 2)
                .test()
                .assertValueAt(0, 1)
                .assertValueAt(1, 2)
                .assertValueCount(2);
    }

    @Test
    public void assertNeverTest() {
        Observable.just(1, 2)
                .test()
                .assertNever(3)
                .assertValueCount(2);
    }

    @Test
    public void exceptionTest() {
        Observable.error(new RuntimeException())
                .test()
                .assertFailure(RuntimeException.class);
    }

    @Test
    public void startWithTest() {
        Observable.error(new RuntimeException())
                .startWith(Observable.just(1, 2))
                .test()
                .assertFailure(RuntimeException.class, 1, 2)
                .assertValueCount(2);
    }

    @Test
    public void failureAndMessageTest() {
        Observable.error(new RuntimeException("Whops"))
                .test()
                .assertFailureAndMessage(RuntimeException.class, "Whops");
    }

    @Test
    public void testObserver() {
        TestObserver<Integer> o = new TestObserver<>();
        Observable.just(1)
                .subscribe(o);

        o.assertValues(1)
                .assertSubscribed()
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void takeUntilTest() {
        String[] data = {"1", "2", "3", "4", "5", "6"};

        Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsed) -> val)
                .takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));

        TestObserver testObserver = new TestObserver();
        source.subscribe(testObserver);

        testObserver.assertSubscribed();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(4);
    }

    @Test
    public void scanTest() {
        String[] balls = {"1", "3", "5"};

        Observable<String> source = Observable.fromArray(balls)
                .scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");

        TestObserver testObserver = new TestObserver();
        source.subscribe(testObserver);

        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(balls.length);
        testObserver.assertResult("1", "3(1)", "5(3(1))");
    }
}
