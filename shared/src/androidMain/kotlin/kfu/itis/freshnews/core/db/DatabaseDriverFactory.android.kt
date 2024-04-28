package kfu.itis.freshnews.core.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.core.configuration.PlatformConfiguration

internal actual class DatabaseDriverFactory {

    actual fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): SqlDriver {
        return AndroidSqliteDriver(
            schema = FreshNews.Schema,
            context = platformConfiguration.androidContext,
            name = name,
        )
    }
}
