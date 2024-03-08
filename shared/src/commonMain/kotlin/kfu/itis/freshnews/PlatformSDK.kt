package kfu.itis.freshnews

import kfu.itis.freshnews.core.configuration.Configuration
import kfu.itis.freshnews.core.configuration.PlatformConfiguration
import kfu.itis.freshnews.core.network.networkModule
import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.bindSingleton
import org.kodein.di.direct

private const val MODULE_NAME = "configurationModule"

object PlatformSDK {

    private var _di: DirectDI? = null

    fun init(configuration: Configuration) {
        _di = DI {
            importAll(
                createConfigurationModule(configuration = configuration),
                networkModule
            )
        }.direct
    }

    private fun createConfigurationModule(configuration: Configuration): DI.Module = DI.Module(
        name = MODULE_NAME,
        init = {
            bindSingleton<Configuration> {
                configuration
            }

            bindSingleton<PlatformConfiguration> {
                configuration.platformConfiguration
            }
        }
    )
}
