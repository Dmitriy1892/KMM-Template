package core.datasource.network.rest.ktor.base.core

import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequestBuilder

interface Interceptor {
    suspend fun Sender.intercept(request: HttpRequestBuilder): HttpClientCall
}