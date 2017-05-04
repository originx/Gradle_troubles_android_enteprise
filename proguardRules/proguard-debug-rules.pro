#
# Add specific proguard rules here that should only apply to debug builds
#
-dontobfuscate

# Additional stuff needed for instrumentation tests (run debug builds of the application under test)
#
# Do not white-list too much here, because this would otherwise defeat the purpose of having a proguarded debug
# version in first instance, since (parts of) these classes are all stripped out during regular release builds!
#
# Also, if you receive a crash because of a non-existant method, ensure that the root cause of this crash resides
# really in the test apk's code. Because if *not*, then the main proguard-rules.pro needs to be adapted since
# this crash will likely pop up in release builds as well!

# Square's MockHttpServer uses okhttp internals, obviously
-dontwarn okhttp3.**
-dontwarn okio.**
# Let us use our common logging infrastructure completely
-keep class timber.log.Timber { *; }
# Special overrides for Rx (we need hooks and blocking observables)
-keep class rx.plugins.** { *; }
-keep class rx.Observable { *; }
-keep class rx.observables.BlockingObservable { *; }

# keep everything from our own namespace, since methods existant in production code can be stripped
# out for release builds if they're only used for testing purposes
-keep class de.companyname.** { *; }

-keep class com.facebook.** { *; }