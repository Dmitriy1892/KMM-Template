package core.datasource.network.rest.ktor.base.core

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.EMPTY
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

internal fun HttpClientConfig<HttpClientEngineConfig>.installLogger(
    isNeedToLogging: Boolean,
    logger: Logger = Logger.SIMPLE,
    logLevel: LogLevel = LogLevel.ALL
) {
    install(Logging) {
        this.logger = if (isNeedToLogging) logger else Logger.EMPTY
        this.level = if (isNeedToLogging) logLevel else LogLevel.NONE
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun HttpClientConfig<HttpClientEngineConfig>.installJsonSerializer(
    json: Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        encodeDefaults = true
        explicitNulls = false
    }
) {
    install(ContentNegotiation) { json(json) }
}

fun HttpClientConfig<HttpClientEngineConfig>.installTimeouts(
    connectTimeoutMillis: Long = 20_000,
    requestTimeoutMillis: Long = 20_000,
    socketTimeoutMillis: Long = 20_000
) {
    install(HttpTimeout) {
        this.connectTimeoutMillis = connectTimeoutMillis
        this.requestTimeoutMillis = requestTimeoutMillis
        this.socketTimeoutMillis = socketTimeoutMillis
    }
}

fun HttpClientConfig<HttpClientEngineConfig>.installBaseUrl(baseUrl: String) {
    defaultRequest {
        url(baseUrl)
    }
}

fun HttpClient.addInterceptor(
    interceptor: Interceptor
): HttpClient {
    this.plugin(HttpSend).intercept { request ->
        with(interceptor) {
            intercept(request)
        }
    }
    return this
}