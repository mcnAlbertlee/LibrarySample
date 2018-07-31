package com.example.albert.librarytest.rx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.albert.librarytest.R;

/**
 * 참고
 * https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/RxOperatorExampleActivity.java
 */
public class RxOperatorExampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_operator);
        setTitle("Rx Operator Example");
    }

    public void startRxActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, RxActivity.class));
    }

    public void startSimpleActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, SimpleExampleActivity.class));
    }

    public void startMapActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, MapExampleActivity.class));
    }

    public void startZipActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, ZipExampleActivity.class));
    }

    public void startDisposableActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, DisposableExampleActivity.class));
    }

    public void startTakeActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, TakeExampleActivity.class));
    }

    public void startTimerActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, TimerExampleActivity.class));
    }

    public void startIntervalActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, IntervalExampleActivity.class));
    }

    public void startSingleObserverActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, SingleObserverExampleActivity.class));
    }

    public void startCompletableObserverActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, CompletableObserverExampleActivity.class));
    }

    public void startFlowableActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, FlowableExampleActivity.class));
    }

    public void startReduceActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, ReduceExampleActivity.class));
    }

    public void startBufferActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, BufferExampleActivity.class));
    }

    public void startFilterActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, FilterExampleActivity.class));
    }

    public void startSkipActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, SkipExampleActivity.class));
    }

    public void startScanActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, ScanExampleActivity.class));
    }

    public void startReplayActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, ReplayExampleActivity.class));
    }

    public void startConcatActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, ConcatExampleActivity.class));
    }

    public void startMergeActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, MergeExampleActivity.class));
    }

    public void startDeferActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, DeferExampleActivity.class));
    }

    public void startDistinctActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, DistinctExampleActivity.class));
    }

    public void startLastOperatorActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, LastOperatorExampleActivity.class));
    }

    public void startReplaySubjectActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, ReplaySubjectExampleActivity.class));
    }

    public void startPublishSubjectActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, PublishSubjectExampleActivity.class));
    }

    public void startBehaviorSubjectActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, BehaviorSubjectExampleActivity.class));
    }

    public void startAsyncSubjectActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, AsyncSubjectExampleActivity.class));
    }

    public void startThrottleFirstActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, ThrottleFirstExampleActivity.class));
    }

    public void startThrottleLastActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, ThrottleLastExampleActivity.class));
    }

    public void startDebounceActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, DebounceExampleActivity.class));
    }

    public void startWindowActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, WindowExampleActivity.class));
    }

    public void startDelayActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, DelayExampleActivity.class));
    }

    public void startSwitchMapActivity(View view) {
        startActivity(new Intent(RxOperatorExampleActivity.this, SwitchMapExampleActivity.class));
    }
}
