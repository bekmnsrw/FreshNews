package kfu.itis.freshnews.core.storage.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.core.configuration.PlatformConfiguration

internal actual class DatabaseDriverFactory {

    actual fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): SqlDriver {
        return NativeSqliteDriver(
            schema = FreshNews.Schema,
            name = name,
        )
    }
}
