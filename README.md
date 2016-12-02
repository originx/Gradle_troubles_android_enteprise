# multidex

Example project which demonstrate espresso multidex issues
And also instant run issues with gradle build scripts

To run please disable instant run

To reproduce multidex issue please run following command

./gradlew clean connectedPayGermanyCompatDebugAndroidTest failed with

run on any device or API 16 emulator
Tests on GTI8190  4.1.2 failed Instrumentation run failed due to java.lang.NoClassDefFoundError

#Issues reported that this repo shows
-https://code.google.com/p/android/issues/detail?id=229205
-https://code.google.com/p/android/issues/detail?id=228449

-Instant run issue for gradle build script (will be reported soon) to run the project in Android Studio make sure to disable instant run
