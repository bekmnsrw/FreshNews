package kfu.itis.freshnews.core.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.config
import io.ktor.client.engine.okhttp.OkHttp
import kfu.itis.freshnews.core.configuration.PlatformConfiguration
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

actual open class HttpEngineFactory actual constructor() {

    private companion object {
        const val HTTP_LOGGING_INTERCEPTOR_TAG = "Network"
    }

    actual open fun createEngine(
        platformConfiguration: PlatformConfiguration
    ): HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp.config {
        config {
            retryOnConnectionFailure(retryOnConnectionFailure = true)
        }

        addInterceptor(
            HttpLoggingInterceptor {
                Timber.tag(tag = HTTP_LOGGING_INTERCEPTOR_TAG).d(message = it)
            }.apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        )
    }
}
