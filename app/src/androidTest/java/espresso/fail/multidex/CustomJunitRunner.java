package espresso.fail.multidex;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.multidex.MultiDex;
import android.support.test.espresso.Espresso;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.runner.lifecycle.ActivityLifecycleCallback;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import io.reactivex.plugins.RxJavaPlugins;

import static android.content.Context.KEYGUARD_SERVICE;
import static android.content.Context.POWER_SERVICE;
import static android.os.PowerManager.*;
import static android.view.WindowManager.LayoutParams.*;


public class CustomJunitRunner extends AndroidJUnitRunner {

    private PowerManager.WakeLock wakeLock;

    @Override
    public void onCreate(Bundle arguments) {
        MultiDex.install(getTargetContext());

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
    public void onStart() {
        String name = CustomJunitRunner.class.getSimpleName();
        Context app = getTargetContext().getApplicationContext();
        // Unlock the device so that the tests can input keystrokes.
        KeyguardManager keyguard = (KeyguardManager) app.getSystemService(KEYGUARD_SERVICE);
        keyguard.newKeyguardLock(name).disableKeyguard();
        // Wake up the screen.
        PowerManager power = (PowerManager) app.getSystemService(POWER_SERVICE);
        wakeLock = power.newWakeLock(FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP | ON_AFTER_RELEASE, name);
        wakeLock.acquire();
        super.onStart();
        //hook up schedulers to rxjava so espresso idling resources can fetch it properly
        //RxJava 2
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> io.reactivex.schedulers.Schedulers.from(AsyncTask
                .THREAD_POOL_EXECUTOR));
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> io.reactivex.schedulers.Schedulers.from(AsyncTask
                .THREAD_POOL_EXECUTOR));
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> io.reactivex.schedulers.Schedulers.from(AsyncTask
                .THREAD_POOL_EXECUTOR));
        RxIdlingResource rxIdlingResource = new RxIdlingResource();
        Espresso.registerIdlingResources(rxIdlingResource);
        RxJavaPlugins.setScheduleHandler(rxIdlingResource);
    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, CustomJunitRunner.class.getName(), context);
    }


}
