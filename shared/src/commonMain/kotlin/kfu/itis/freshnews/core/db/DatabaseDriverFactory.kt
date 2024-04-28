package kfu.itis.freshnews.core.db

import app.cash.sqldelight.db.SqlDriver
import kfu.itis.freshnews.core.configuration.PlatformConfiguration

internal expect class DatabaseDriverFactory() {

    fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): SqlDriver
}
