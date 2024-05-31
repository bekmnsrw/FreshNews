package kfu.itis.freshnews.core.storage.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import kfu.itis.freshnews.core.configuration.PlatformConfiguration

internal actual class SettingsFactory {

    actual fun create(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): Settings {
        return NSUserDefaultsSettings.Factory().create(name)
    }
}
