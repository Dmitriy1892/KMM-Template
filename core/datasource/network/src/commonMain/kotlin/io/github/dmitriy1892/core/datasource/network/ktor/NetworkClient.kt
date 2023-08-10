package io.github.dmitriy1892.core.datasource.network.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
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
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.sync.Mutex
import kotlinx.serialization.json.Json

class NetworkClient(
    private val baseUrl: String,
    engine: HttpClientEngineFactory<HttpClientEngineConfig>,
    private val loggingSettings: LoggingSettings,
    private val credentialsManager: CredentialsManager
) {

    private val mutex = Mutex()
    private var reauthCount = 0

    val client = HttpClient(engine) {
        install(Logging) {
            val isNeedToLog = loggingSettings.isNeedToLogging
            logger = if (isNeedToLog) Logger.SIMPLE else Logger.EMPTY
            level = if (isNeedToLog) LogLevel.ALL else LogLevel.NONE
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(HttpTimeout) {
            connectTimeoutMillis = 20000
            requestTimeoutMillis = 20000
            socketTimeoutMillis = 20000
        }

        defaultRequest {
            url(baseUrl)
        }
    }
        .addAuthTokenInterceptor()
        .addAuthenticatorInterceptor()

    private fun HttpClient.addAuthTokenInterceptor(): HttpClient {
        return this.apply {
            plugin(HttpSend).intercept { request ->

                if (request.headers[NO_AUTH_HEADER] == null) {
                    val token = credentialsManager.getAccessToken()
                    request.headers[AUTHORIZATION_HEADER] = "Bearer $token"
                } else {
                    request.headers.remove(NO_AUTH_HEADER)
                }

                execute(request)
            }
        }
    }

    private fun HttpClient.addAuthenticatorInterceptor(): HttpClient {
        return this.apply {
            plugin(HttpSend).intercept { request ->
                var originalCall = execute(request)

                mutex.lock()
                val isUnauthorized = originalCall.response.status.value == 401
                val isReauthPossible = reauthCount <= 2

                if (isUnauthorized && isReauthPossible) {
                    reauthCount++
                    mutex.unlock()

                    val newAccessToken = refreshToken()
                    request.headers[AUTHORIZATION_HEADER] = "Bearer $newAccessToken"

                    originalCall = execute(request)
                } else {
                    reauthCount = 0
                    mutex.unlock()
                }

                originalCall
            }
        }
    }

    private suspend fun HttpClient.refreshToken(): String {
        TODO("ADD VALID MODELS AFTER API IMPLEMENTATION")
        try {
            val refreshToken = credentialsManager.getRefreshToken()
            val request = Any()
            val response = this.post<Any>(
                urlString = "/api/refresh-token",
                requestBody = request,
                headers = headersOf(NO_AUTH_HEADER, NO_AUTH_HEADER)
            )
            val newAccessToken = "response.data.accessToken"
            val newRefreshToken = "response.data.refreshToken"
            credentialsManager.updateAccessToken(newAccessToken)
            credentialsManager.updateRefreshToken(newRefreshToken)

            return newAccessToken
        } catch (e: Throwable) {
            return ""
        }
    }
}