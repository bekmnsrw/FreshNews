package kfu.itis.freshnews.core.configuration

data class Configuration(
    val platformConfiguration: PlatformConfiguration,
    val isHttpLoggingEnabled: Boolean,
    val isDebug: Boolean
) {

    enum class DeviceType {
        Tablet,
        Phone
    }
}
