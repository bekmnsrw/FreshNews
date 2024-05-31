package kfu.itis.freshnews.core.storage.settings

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import kfu.itis.freshnews.core.configuration.PlatformConfiguration

internal actual class SettingsFactory {

    actual fun create(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): Settings {
        return SharedPreferencesSettings(
            delegate = platformConfiguration
                .androidContext
                .getSharedPreferences(name, Context.MODE_PRIVATE)
        )
    }
}
