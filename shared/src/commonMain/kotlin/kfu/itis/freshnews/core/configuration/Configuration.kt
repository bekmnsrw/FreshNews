package kfu.itis.freshnews.core.configuration

import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding

data class Configuration(
    val platformConfiguration: PlatformConfiguration,
    val isHttpLoggingEnabled: Boolean,
    val isDebug: Boolean,
    val firebaseCrashlyticsBinding: FirebaseCrashlyticsBinding,
) {

    enum class DeviceType {
        Tablet,
        Phone,
    }
}
