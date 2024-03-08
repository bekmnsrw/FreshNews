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
import io.ktor.serialization.kotlinx.json.json
import kfu.itis.freshnews.core.configuration.Configuration
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

private const val MODULE_NAME = "networkModule"
private const val BASE_URL = "https://newsapi.org/v2/"
private const val CONNECT_TIMEOUT_MILLIS = 15000L
private const val REQUEST_TIMEOUT_MILLIS = 30000L
private const val SOCKET_TIMEOUT_MILLIS = 30000L

val networkModule = DI.Module(name = MODULE_NAME) {

    bindSingleton<HttpEngineFactory> {
        HttpEngineFactory()
    }

    bindSingleton<Json> {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    bindSingleton<HttpClient> {
        buildHttpClient(
            httpClientEngine = instance(),
            json = instance(),
            configuration = instance()
        )
    }
}

private fun buildHttpClient(
    httpClientEngine: HttpClientEngineFactory<HttpClientEngineConfig>,
    json: Json,
    configuration: Configuration
) = HttpClient(httpClientEngine) {

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

    defaultRequest {
        url {
            this.host = BASE_URL
            this.protocol = URLProtocol.HTTPS
        }
    }
}
