package kfu.itis.freshnews.core.storage.settings

import com.russhwolf.settings.Settings
import kfu.itis.freshnews.core.configuration.PlatformConfiguration

internal expect class SettingsFactory() {

    fun create(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): Settings
}
