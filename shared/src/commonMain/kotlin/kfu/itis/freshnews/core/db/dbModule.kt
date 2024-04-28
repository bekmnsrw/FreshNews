package kfu.itis.freshnews.core.db

import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.core.configuration.PlatformConfiguration
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

private const val MODULE_NAME = "dbModule"
private const val DB_NAME = "FreshNews.db"

val dbModule = DI.Module(name = MODULE_NAME) {

    bindSingleton<FreshNews> {
        val dbDriver = DatabaseDriverFactory().createDriver(
            name = DB_NAME,
            platformConfiguration = instance<PlatformConfiguration>(),
        )
        FreshNews(dbDriver)
    }
}
