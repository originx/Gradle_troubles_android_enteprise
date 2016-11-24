#
# Add specific proguard rules here that should only apply to test app debug builds
#
-dontobfuscate
-ignorewarnings
-keepattributes Exceptions,InnerClasses

-dontwarn org.mockito.**
-dontwarn org.objenesis.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**
-dontwarn org.bouncycastle.**
-dontwarn com.squareup.**
-keep, includedescriptorclasses class com.squareup.** {*;}
-dontwarn android.test.**
-dontwarn android.support.test.**

# Specific classes that common test libs warn about
-dontwarn java.beans.**
-dontwarn javax.lang.model.element.Modifier
-dontwarn org.apache.tools.ant.**
-dontwarn org.assertj.core.**
-dontwarn org.easymock.**
-dontwarn org.jmock.core.**
-dontwarn org.w3c.dom.bootstrap.**
-dontwarn sun.misc.Unsafe
-dontwarn sun.reflect.**
-dontwarn net.bytebuddy.**
-keep class java.beans.** { *; }
-dontwarn retrofit.MockRestAdapter.**
-dontwarn rx.**
-dontwarn org.hamcrest.**
-keep, includedescriptorclasses class org.hamcrest.** { *; }
-keep, includedescriptorclasses class java.util.** { *; }
-keep, includedescriptorclasses class java.util.Executor { *; }
-keepclassmembers class java.util.** { *; }
-keepclassmembers class java.util.xxxx { *; }
-keepclassmembers interface * extends java.util.**{*;}
-keep, includedescriptorclasses class rx.** { *; }
-keep class rx.schedulers.ImmediateScheduler {
    *;
}
-keep class rx.schedulers.TestScheduler {
    *;
}
# rxjava
-keep class rx.schedulers.Schedulers {
    *;
}

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

-keepclassmembers class rx.xxxx { public *; }

-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
# retrofit
-dontwarn com.squareup.**
-keepclassmembers class java.beans.xxxx { public *; }
-keep class rx.** { *; }
-keepclassmembers class rx.xxxx { *; }
-keepclassmembers class org.assertj.xxxx { public *; }
-keepclassmembers class org.junit.xxxx { public *; }
-keepclassmembers class java.beans.xxxx { public *; }
-keepclassmembers class android.test.xxxx { public *; }

-keepclassmembers class org.joda.time.xxxx { public *; }
-keep class org.joda.time.** { *; }
-keep class android.support.test.** { *; }
-keep class org.hamcrest.** { *; }
-keep class rx.plugins.** { *;}

-keep class android.support.v7.widget.RecyclerView { *; }
-keep class android.* { *; }

#okhttp3
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

#adition
-keep class com.adition.android.sdk.** {*;}
-dontwarn com.adition.android.sdk.**
