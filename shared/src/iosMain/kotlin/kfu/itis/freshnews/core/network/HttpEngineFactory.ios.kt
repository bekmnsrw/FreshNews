package kfu.itis.freshnews.core.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import kfu.itis.freshnews.core.configuration.PlatformConfiguration

actual open class HttpEngineFactory actual constructor() {

    actual open fun createEngine(
        platformConfiguration: PlatformConfiguration
    ): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin
}
