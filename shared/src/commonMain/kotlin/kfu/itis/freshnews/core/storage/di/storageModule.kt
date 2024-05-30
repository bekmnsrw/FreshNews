package kfu.itis.freshnews.core.storage.di

import com.russhwolf.settings.Settings
import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.core.configuration.PlatformConfiguration
import kfu.itis.freshnews.core.storage.db.DatabaseDriverFactory
import kfu.itis.freshnews.core.storage.settings.SettingsFactory
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

private const val MODULE_NAME = "storageModule"
private const val DB_NAME = "FreshNews.db"
private const val SETTINGS_NAME = "FreshNews.settings"

val storageModule = DI.Module(MODULE_NAME) {

    bindSingleton<FreshNews> {
        val dbDriver = DatabaseDriverFactory().createDriver(
            name = DB_NAME,
            platformConfiguration = instance<PlatformConfiguration>(),
        )
        FreshNews(dbDriver)
    }

    bindSingleton<Settings> {
        SettingsFactory().create(
            name = SETTINGS_NAME,
            platformConfiguration = instance<PlatformConfiguration>(),
        )
    }
}
