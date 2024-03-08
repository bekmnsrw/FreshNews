package kfu.itis.freshnews.core.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import kfu.itis.freshnews.core.configuration.PlatformConfiguration

expect open class HttpEngineFactory() {

    fun createEngine(
        platformConfiguration: PlatformConfiguration
    ): HttpClientEngineFactory<HttpClientEngineConfig>
}
