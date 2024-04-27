package kfu.itis.freshnews.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kfu.itis.freshnews.core.configuration.Configuration
import kfu.itis.freshnews.core.configuration.PlatformConfiguration
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

private const val MODULE_NAME = "networkModule"
private const val HOST = "newsapi.org"
private const val BASE_URL = "v2/"
private const val API_KEY_REQUEST_PARAMETER = "apiKey"
private const val API_KEY = "91085fe3aa7e4cb2902b927389757f59"
private const val CONNECT_TIMEOUT_MILLIS = 15000L
private const val REQUEST_TIMEOUT_MILLIS = 30000L
private const val SOCKET_TIMEOUT_MILLIS = 30000L

val networkModule = DI.Module(name = MODULE_NAME) {

    bindSingleton<HttpClientEngineFactory<HttpClientEngineConfig>> {
        provideHttpClientEngineFactory(
            platformConfiguration = instance<PlatformConfiguration>()
        )
    }

    bindSingleton<Json> {
        provideJson()
    }

    bindSingleton<HttpClient> {
        provideHttpClient(
            httpClientEngine = instance<HttpClientEngineFactory<HttpClientEngineConfig>>(),
            json = instance<Json>(),
            configuration = instance<Configuration>()
        )
    }
}

private fun provideHttpClientEngineFactory(
    platformConfiguration: PlatformConfiguration
): HttpClientEngineFactory<HttpClientEngineConfig> = HttpEngineFactory().createEngine(
    platformConfiguration = platformConfiguration
)

private fun provideJson(): Json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    prettyPrint = true
}

private fun provideHttpClient(
    httpClientEngine: HttpClientEngineFactory<HttpClientEngineConfig>,
    json: Json,
    configuration: Configuration
): HttpClient = HttpClient(httpClientEngine) {

    if (configuration.isHttpLoggingEnabled) {
        install(plugin = Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
    }

    install(plugin = ContentNegotiation) {
        json(json = json)
    }

    install(plugin = HttpTimeout) {
        connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
        requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
        socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
    }

    /*
     * API documentation: https://newsapi.org/docs/get-started
     */
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = HOST
            path(BASE_URL)
            parameters.append(API_KEY_REQUEST_PARAMETER, API_KEY)
        }
    }
}
