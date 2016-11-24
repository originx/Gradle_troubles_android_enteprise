package espresso.fail.multidex;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.runner.lifecycle.ActivityLifecycleCallback;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

import static android.view.WindowManager.LayoutParams.*;

/**
 * Created by morsolic on 23/11/16.
 */

public class CustomJunitRunner extends AndroidJUnitRunner {


    @Override
    public void onCreate(Bundle arguments) {
        MultiDex.install(getTargetContext());
        //hook up schedulers to rxjava so espresso idling resouces can fetch it properly
        RxJavaHooks.setOnComputationScheduler(current -> Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR));
        RxJavaHooks.setOnIOScheduler(current -> Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR));
        RxJavaHooks.setOnNewThreadScheduler(current -> Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR));

        super.onCreate(arguments);

        ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(new ActivityLifecycleCallback() {
            @Override
            public void onActivityLifecycleChanged(Activity activity, Stage stage) {
                if (stage == Stage.PRE_ON_CREATE) {
                    activity.getWindow().addFlags(FLAG_DISMISS_KEYGUARD | FLAG_TURN_SCREEN_ON | FLAG_KEEP_SCREEN_ON);
                }
            }
        });


    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestCustomApplication.class.getName(), context);
    }

}
