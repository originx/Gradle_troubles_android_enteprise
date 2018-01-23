# Add project specific ProGuard rules here.
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepattributes Signature
-keepattributes *Annotation*

# retrofit proguard
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

# we need this to keep our model classes
-keep class de.companyname.core.data.network.api.model.** { *; }

-keepclassmembernames interface * {
    @retrofit.http.* <methods>;
}


# Joda Time
-dontwarn org.joda.convert.**
-dontwarn org.joda.time.**
-keep class org.joda.time.** { *; }
-keep interface org.joda.time.** { *; }

#okhttp3
-dontwarn okhttp3.**
-dontnote okhttp3.**

# Okio
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**

# crashlytics
-keepattributes SourceFile,LineNumberTable
-keepattributes *Annotation*
-keep public class * extends java.lang.Exception

# event bus method removal stopped, see https://github.com/greenrobot/EventBus/issues/133
-keep,includedescriptorclasses class ** {
public void onEvent*(**);
    void onEvent*(**);}
-keep,includedescriptorclasses class de.greenrobot.event.EventBus { *; }

# facebook conceal
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.crypto.proguard.annotations.DoNotStrip
-keep,allowobfuscation @interface com.facebook.crypto.proguard.annotations.KeepGettersAndSetters
-dontwarn org.apache.harmony.xnet.provider.jsse.NativeCrypto
-dontnote org.apache.harmony.xnet.provider.jsse.NativeCrypto

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.crypto.proguard.annotations.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.crypto.proguard.annotations.DoNotStrip *;
}

-keepclassmembers @com.facebook.crypto.proguard.annotations.KeepGettersAndSetters class * {
  void set*(***);
  *** get*();
}


# komensky validation lib
-keepattributes *Annotation*
-keep class eu.inmite.android.lib.validations.form.annotations.** { *; }
-keep class de.companyname.paylib.util.PinValidation  { *; }
-keep class de.companyname.paylib.util.RegexOrderValidation  { *; }
-keep class * implements eu.inmite.android.lib.validations.form.iface.ICondition
-keep class * implements eu.inmite.android.lib.validations.form.iface.IValidator
-keep class * implements eu.inmite.android.lib.validations.form.iface.IFieldAdapter
-keepclassmembers class ** {
    @eu.inmite.android.lib.validations.form.annotations.** *;
}

## Adjust
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class com.adjust.sdk.plugin.MacAddressUtil {
    java.lang.String getMacAddress(android.content.Context);
}
-keep class com.adjust.sdk.plugin.AndroidIdUtil {
    java.lang.String getAndroidId(android.content.Context);
}
-keep class com.google.android.gms.common.ConnectionResult {
    int SUCCESS;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {
    com.google.android.gms.ads.identifier.AdvertisingIdClient$Info getAdvertisingIdInfo(android.content.Context);
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {
    java.lang.String getId();
    boolean isLimitAdTrackingEnabled();
}

-dontnote  android.os.SystemProperties
-dontwarn android.os.SystemProperties

-keep class dalvik.system.VMRuntime {
    java.lang.String getRuntime();
}
-dontwarn dalvik.system.VMRuntime
-dontnote dalvik.system.VMRuntime

-keep class android.os.Build {
    java.lang.String[] SUPPORTED_ABIS;
    java.lang.String CPU_ABI;
}
-dontnote android.os.Build
-dontwarn android.os.Build
-dontnote android.os.Build$*
-dontwarn android.os.Build$*
-keep class android.content.res.Configuration {
    android.os.LocaledList getLocales();
    java.util.Locale locale;
}


-keep class android.os.LocaledList {
    java.util.Locale get(int);
}
-dontwarn android.os.LocaledList
-dontnote android.os.LocaledList

-dontwarn android.content.res.Configuration
-dontnote android.content.res.Configuration

-keep class de.companyname.paylib.data.**{
*;
<fields>;
public protected *;}

-keep class de.companyname.paylib.nfc.models.**{
*;
<fields>;
public protected *;}


-keep class de.companyname.paylib.parcelables.**{
*;
<fields>;
public protected *;}

-keep,includedescriptorclasses class *  extends de.companyname.core.tracking.adjust.IAdjustEvent{
*;
<fields>;
public protected *;}

-keep class de.companyname.paylib.events.**{
*;
<fields>;
public protected *;}
-keep class de.companyname.core.events.**{
*;
<fields>;
public protected *;}

-keep class de.companyname.core.data.coupon.events.**{
*;
<fields>;
public protected *;}

-keep class de.companyname.core.util.networking.NetworkChangedEvent{
*;
<fields>;
public protected *;}

-keep class de.companyname.app.ui.onlineshopping.ads.AdAreaEvent{
*;
<fields>;
public protected *;}

-keep class de.companyname.app.ui.coupons.CouponActivatedEvent{
*;
<fields>;
public protected *;}

-keep class de.companyname.app.ui.couponfilter.service.CouponFilterChangedEvent{
*;
<fields>;
public protected *;}
##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-dontwarn sun.misc.Unsafe
-dontnote sun.misc.Unsafe


# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
#Note: com.google.gson.internal.UnsafeAllocator accesses a declared field 'theUnsafe' dynamically
-keep class com.google.gson.internal.UnsafeAllocator { java.lang.reflect.Field theUnsafe; }
-dontnote  com.google.gson.internal.UnsafeAllocator
-dontwarn  com.google.gson.internal.UnsafeAllocator


#Resulting Note: the configuration refers to the unknown field 'java.lang.reflect.Field theUnsafe' in class 'com.google.gson.internal.UnsafeAllocator'

# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
# For using GSON @Expose annotation
-keepattributes EnclosingMethod

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
##---------------End: proguard configuration for Gson  ----------

# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# don't warn about missing wearable stuff in Adobe's Marketing Cloud
-dontwarn com.adobe.mobile.*Wearable*
-dontwarn com.adobe.mobile.ConfigSynchronizer*
-dontwarn com.adobe.mobile.DataListenerHandheld

# retrolambda
-dontwarn java.lang.invoke.*

# ignore warnings that stem from code coverage code
-dontwarn org.jacoco.**

#zxing
-keep class com.google.zxing.** { *; }

#easing animation lib
-keep class com.daimajia.easing.** { *; }
-keep interface com.daimajia.easing.** { *; }

#Cupboard
# we need this to keep our model classes
-keep class de.companyname.core.data.coupon.Coupon { *; }
-keep class de.companyname.core.data.coupon.OriginalCoupon { *; }
-keep class de.companyname.core.data.partner.OriginalPartnerList { *; }
-keep class de.companyname.core.data.accountbalance.AccountTransaction { *; }
-keep class de.companyname.core.data.accountbalance.AccountBalance { *; }
-keep class de.companyname.core.data.feed.TileItem { *; }
-keep class de.companyname.core.data.accountbalance.EventDetail { *; }
-keep,includedescriptorclasses class de.companyname.core.config.** { *;
  <fields>;}
-keep,includedescriptorclasses class de.companyname.core.config.** { *;
 <fields>;}
-keep class de.companyname.core.config.** {
 <fields>;
     *;
  }

  # To support Enum type of class members
  -keep enum * { *; }
## Nineolddroid related classes to ignore

-keep,includedescriptorclasses class com.nineoldandroids.animation.** { *; }
-keep,includedescriptorclasses interface com.nineoldandroids.animation.** { *; }
-keep,includedescriptorclasses class com.nineoldandroids.view.** { *; }
-keep,includedescriptorclasses interface com.nineoldandroids.view.** { *; }

#descriptor classes
-keep,includedescriptorclasses class com.google.gson.JsonElement { *; }
-keep,includedescriptorclasses class com.google.gson.reflect.TypeToken { *; }
-keep,includedescriptorclasses class de.companyname.core.data.accountbalance.AccountTransactionDetail  { *; }
-keep,includedescriptorclasses class de.companyname.paylib.data.accountbalance.PayAccountTransaction { *; }
-keep,includedescriptorclasses class de.companyname.core.data.user.User { *; }
-keep,includedescriptorclasses class de.companyname.app.ui.coupons.CouponView { *; }
-keep,includedescriptorclasses interface de.companyname.core.ui.animation.IAnimationListener { *; }
-keep,includedescriptorclasses class de.companyname.core.data.partner.OnlineShoppingElement { *; }
-keep,includedescriptorclasses class com.google.gson.JsonSerializationContext { *; }
-keep,includedescriptorclasses class com.google.gson.JsonDeserializationContext { *; }
-keep,includedescriptorclasses class de.companyname.core.config.** { *; }
-keep,includedescriptorclasses class de.companyname.core.data.persistence.cache.CachePolicy { *; }
-keep,includedescriptorclasses class de.companyname.core.data.network.api.RestAPICore { *; }

#paylib
-keep class de.companyname.paylib.ui.widget.PayToolbarSwitcher { *; }
-keepclassmembers,includedescriptorclasses class de.companyname.paylib.ui.widget.PayToolbarSwitcher { *; }
-keep class de.companyname.paylib.ui.widget.PayToolbarSwitcher$* {
    *;
    **[] $VALUES;
    public *;
    <fields>;}

# Proximity
-keep class net.companyname.proximity.sdk.** { *;}
-keepattributes Exceptions, InnerClasses, Annotation, Signature, EnclosingMethod
-keep public enum net.companyname.proximity.sdk.** {
**[] $VALUES;
public *;
}

#rxjava
-dontnote rx.internal.util.PlatformDependent
-dontwarn rx.internal.util.PlatformDependent

#https://stackoverflow.com/questions/33047806/proguard-duplicate-definition-of-library-class
-dontnote android.net.http.*
-dontnote org.apache.commons.codec.**
-dontnote org.apache.http.**

# Glide Module
-keep public class * implements com.bumptech.glide.module.GlideModule

-keepnames class * implements android.os.Parcelable
-keepclassmembers class * implements android.os.Parcelable {
  public static final *** CREATOR;
}

-keep @interface android.support.annotation.Keep
-keep @android.support.annotation.Keep class *
-keepclasseswithmembers class * {
  @android.support.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
  @android.support.annotation.Keep <methods>;
}

-dontwarn android.security.NetworkSecurityPolicy