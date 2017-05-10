# multidex

Example project which demonstrate espresso multidex issues
And also instant run issues with gradle build scripts <-- disable instant run if you want to use Android studio with this

To run please disable instant run

# Open:

- Instant run does not run with this project last time i checked, please disable it

# Partially open:

(tl;dr; still there, if you want multidex on 2.4>= either wait for multidex support or patch the multidex yourself on test runner)
- Multidex fails with gradle 2.4.0-alpha7 plugin while running tests
https://issuetracker.google.com/issues/37324038
https://issuetracker.google.com/issues/38173162


# RESOLVED:

To reproduce multidex issue please run following command

./gradlew clean connectedPayGermanyCompatDebugAndroidTest failed with

run on any device or API 16 emulator
Tests on GTI8190  4.1.2 failed Instrumentation run failed due to java.lang.NoClassDefFoundError

#Issues reported that this repo shows

-https://code.google.com/p/android/issues/detail?id=229205

-https://code.google.com/p/android/issues/detail?id=228449

#Explanation by Google dev:

The issue is that the rx.plugins.RxJavaHooks class referenced from the CustomJunitRunner.onCreate() method is in the secondary dex file of the main app, and you are accessing it before the class loaders get fully patched.

When the main application and test code share a dependency, we will remove it from the test's dependencies (as we expect it to be available in the main application). However, with legacy multidex, this is causing problems.

Currently, there are 2 workarounds:

Option 1 Ensure the rx.plugins.RxJavaHooks is in the main dex by creating a file multidexKeepProguard.pro and adding "-keep class rx.plugins.**"

Option 2 Remove references to RxJavaHooks from onCreate(), and move them to onStart() (not sure if this accomplishes when you want though): @Override public void onStart() { super.onStart(); //hook up schedulers to rxjava so espresso idling resouces can fetch it properly RxJavaHooks.setOnComputationScheduler(current -> Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)); RxJavaHooks.setOnIOScheduler(current -> Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)); RxJavaHooks.setOnNewThreadScheduler(current -> Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)); }
Solution

#Workaround

So current workaround would be either use multidexKeepProguard.pro file and in your debug config point to that file:

 debug {
            applicationIdSuffix '.debug'
            multiDexKeepProguard file('../proguardRules/multidex-proguard.pro')
        }
Your multidex proguard file should contain classes which are not being found in the main dex file, in my case it was RxJavaPlugin, so my multidexproguard file contains:

-keep class rx.** { *; }
