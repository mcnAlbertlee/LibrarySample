package dagger.android;

import android.app.Activity;
import android.util.Log;

import io.reactivex.functions.Consumer;

public class AndroidInjection {

    private static Consumer<Activity> consumer;

    public static void mocking(Consumer<Activity> consumer){
        AndroidInjection.consumer = consumer;
    }

    public static void inject(Activity activity) {
        Log.i("TESTCASE", activity.getLocalClassName());
        try {
            consumer.accept(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
