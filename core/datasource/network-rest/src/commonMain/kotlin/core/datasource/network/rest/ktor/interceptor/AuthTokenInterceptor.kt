package core.datasource.network.rest.ktor.interceptor

import core.datasource.network.rest.ktor.base.AUTHORIZATION_HEADER
import core.datasource.network.rest.ktor.CredentialsManager
import core.datasource.network.rest.ktor.base.NO_AUTH_HEADER
import core.datasource.network.rest.ktor.base.core.Interceptor
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequestBuilder
import org.koin.core.annotation.Single

@Single
class AuthTokenInterceptor(
    private val credentialsManager: CredentialsManager
) : Interceptor {

    override suspend fun Sender.intercept(request: HttpRequestBuilder): HttpClientCall {
//        if (request.headers[NO_AUTH_HEADER] == null) {
//            val token = credentialsManager.getAccessToken()
//            request.headers[AUTHORIZATION_HEADER] = "Bearer $token"
//        } else {
//            request.headers.remove(NO_AUTH_HEADER)
//        }

        return execute(request)
    }
}