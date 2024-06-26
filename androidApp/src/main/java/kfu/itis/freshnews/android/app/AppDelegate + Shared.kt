package kfu.itis.freshnews.android.app

import android.os.Build
import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.android.BuildConfig
import kfu.itis.freshnews.android.firebase.AndroidFirebaseAnalytics
import kfu.itis.freshnews.android.firebase.AndroidFirebaseCrashlytics
import kfu.itis.freshnews.core.configuration.Configuration
import kfu.itis.freshnews.core.configuration.PlatformConfiguration
import kfu.itis.freshnews.utils.deviceType

fun AppDelegate.initShared() {

    val configuration = Configuration(
        platformConfiguration = PlatformConfiguration(
            androidContext = applicationContext,
            appVersionName = BuildConfig.VERSION_NAME,
            appVersionNumber = BuildConfig.VERSION_CODE.toString(),
            osVersion = Build.VERSION.RELEASE.toString(),
            deviceType = resources.deviceType,
        ),
        isDebug = BuildConfig.DEBUG,
        isHttpLoggingEnabled = BuildConfig.DEBUG,
        firebaseCrashlyticsBinding = AndroidFirebaseCrashlytics(),
        firebaseAnalyticsBinding = AndroidFirebaseAnalytics(),
    )

    PlatformSDK.init(configuration = configuration)
}
