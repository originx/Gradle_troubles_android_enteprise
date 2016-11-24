# multidex

Example project which demonstrate espresso multidex issues
And also instant run issues with gradle build scripts

To run please disable instant run

To reproduce multidex issue please run following command

./gradlew clean connectedPayGermanyCompatDebugAndroidTest failed with

run on any device or API 16 emulator
Tests on GTI8190  4.1.2 failed Instrumentation run failed due to java.lang.NoClassDefFoundError
