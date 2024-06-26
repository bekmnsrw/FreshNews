package kfu.itis.freshnews.core.di

import kfu.itis.freshnews.core.configuration.Configuration
import kfu.itis.freshnews.core.configuration.PlatformConfiguration
import kfu.itis.freshnews.core.storage.di.storageModule
import kfu.itis.freshnews.core.firebase.FirebaseAnalyticsBinding
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.core.network.networkModule
import kfu.itis.freshnews.feature.auth.di.authModule
import kfu.itis.freshnews.feature.details.di.detailsModule
import kfu.itis.freshnews.feature.favorites.di.favoritesModule
import kfu.itis.freshnews.feature.home.di.homeModule
import kfu.itis.freshnews.feature.profile.di.profileModule
import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.LazyDelegate
import org.kodein.di.bindSingleton
import org.kodein.di.direct
import org.kodein.di.instance

private const val MODULE_NAME = "configurationModule"

object PlatformSDK {

    private var _di: DirectDI? = null

    val di: DirectDI
        get() = requireNotNull(_di)

    fun init(configuration: Configuration) {
        _di = DI {
            importAll(
                createConfigurationModule(configuration = configuration),
                networkModule,
                storageModule,
                homeModule,
                detailsModule,
                favoritesModule,
                authModule,
                profileModule,
            )
        }.direct
    }

    private fun createConfigurationModule(configuration: Configuration): DI.Module = DI.Module(
        name = MODULE_NAME,
        init = {
            bindSingleton<Configuration> { configuration }
            bindSingleton<PlatformConfiguration> { configuration.platformConfiguration }
            bindSingleton<FirebaseCrashlyticsBinding> { configuration.firebaseCrashlyticsBinding }
            bindSingleton<FirebaseAnalyticsBinding> { configuration.firebaseAnalyticsBinding }
        }
    )

    inline fun <reified T : Any> lazyInstance(tag: Any? = null): LazyDelegate<T> {
        return di.lazy.instance(tag)
    }
}
