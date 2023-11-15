package core.datasource.network.rest.ktor.interceptor

import core.datasource.network.rest.ktor.base.AUTHORIZATION_HEADER
import core.datasource.network.rest.ktor.base.BASE_URL_QUALIFIER
import core.datasource.network.rest.ktor.CredentialsManager
import core.datasource.network.rest.ktor.base.NO_AUTH_HEADER
import core.datasource.network.rest.ktor.base.client.HttpClientFactory
import core.datasource.network.rest.ktor.base.core.Interceptor
import core.datasource.network.rest.ktor.base.post
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.headersOf
import kotlinx.coroutines.sync.Mutex
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class RefreshTokenInterceptor(
    @Named(BASE_URL_QUALIFIER) private val baseUrl: String,
    private val credentialsManager: CredentialsManager
) : Interceptor {

    private val mutex = Mutex()
    private var reauthCount = 0

    override suspend fun Sender.intercept(request: HttpRequestBuilder): HttpClientCall {
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

        return originalCall
    }

    private suspend fun refreshToken(): String {
        try {
            val refreshToken = credentialsManager.getRefreshToken()

            val client = HttpClientFactory.create(baseUrl)

            val response = client.post<Any>(
                urlString = "/api/refresh-token",
                requestBody = refreshToken,
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