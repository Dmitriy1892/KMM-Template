package io.github.dmitriy1892.core.datasource.network.ktor

import io.github.dmitriy1892.core.domain.exception.NetworkException
import io.github.dmitriy1892.core.domain.exception.UnknownException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.expectSuccess
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.Parameters
import io.ktor.http.content.NullBody
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.http.parametersOf

internal const val NO_AUTH_HEADER = "NoAuth"

internal const val AUTHORIZATION_HEADER = "Authorization"

internal suspend inline fun <reified T> HttpClient.post(
    urlString: String,
    requestBody: Any,
    headers: Headers = headersOf(),
    queries: Parameters = parametersOf()
): T {
    return wrapCall {
        this.post {
            prepareRequest(urlString, headers, queries)
            setBody(requestBody)
        }.body()
    }
}

internal suspend inline fun <reified T> HttpClient.post(
    urlString: String,
    headers: Headers = headersOf(),
    queries: Parameters = parametersOf()
): T {
    return wrapCall {
        this.post {
            prepareRequest(urlString, headers, queries)
            setBody(NullBody)
        }.body()
    }
}

internal suspend inline fun <reified T> HttpClient.get(
    urlString: String,
    headers: Headers = headersOf(),
    queries: Parameters = parametersOf()
): T {
    return wrapCall {
        this.get { prepareRequest(urlString, headers, queries) }.body()
    }
}

internal suspend inline fun <reified T> HttpClient.put(
    urlString: String,
    requestBody: Any,
    headers: Headers = headersOf(),
    queries: Parameters = parametersOf()
): T {
    return wrapCall {
        this.put {
            prepareRequest(urlString, headers, queries)
            setBody(requestBody)
        }.body()
    }
}

internal suspend inline fun <reified T> HttpClient.put(
    urlString: String,
    headers: Headers = headersOf(),
    queries: Parameters = parametersOf()
): T {
    return wrapCall {
        this.put {
            prepareRequest(urlString, headers, queries)
            setBody(NullBody)
        }.body()
    }
}

internal suspend inline fun <reified T> HttpClient.patch(
    urlString: String,
    requestBody: Any,
    headers: Headers = headersOf(),
    queries: Parameters = parametersOf()
): T {
    return wrapCall {
        this.patch {
            prepareRequest(urlString, headers, queries)
            setBody(requestBody)
        }.body()
    }
}

internal suspend inline fun <reified T> HttpClient.patch(
    urlString: String,
    headers: Headers = headersOf(),
    queries: Parameters = parametersOf()
): T {
    return wrapCall {
        this.patch {
            prepareRequest(urlString, headers, queries)
            setBody(NullBody)
        }.body()
    }
}

internal suspend inline fun <reified T> HttpClient.delete(
    urlString: String,
    headers: Headers = headersOf(),
    queries: Parameters = parametersOf()
): T {
    return wrapCall {
        this.delete { prepareRequest(urlString, headers, queries) }.body()
    }
}

internal suspend inline fun <reified T> HttpClient.delete(
    urlString: String, 
    requestBody: Any,
    headers: Headers = headersOf(),
    queries: Parameters = parametersOf()
): T {
    return wrapCall {
        this.delete(urlString) {
            prepareRequest(urlString, headers, queries)
            setBody(requestBody)
        }.body()
    }
}

internal suspend inline fun <reified T> HttpClient.wrapCall(crossinline block: suspend () -> T): T {
    return try {
        block()
    } catch (e: NetworkException) {
        throw e
    } catch (e: RedirectResponseException) {
        // 3xx - responses
        throw NetworkException.RedirectException(e.message, e.cause)
    } catch (e: ClientRequestException) {
        // 4xx - responses
        val mappedError = when (e.response.status.value) {
            401 -> NetworkException.UnauthorizedException(e.message, e.cause)
            404 -> NetworkException.NotFoundException(e.message, e.cause)
            else -> NetworkException.ClientSideException(e.message, e.cause)
        }
        throw mappedError
    } catch (e: ServerResponseException) {
        // 5xx - responses
        throw NetworkException.ServerSideException(e.message, e.cause)
    } catch (e: ConnectTimeoutException) {
        throw NetworkException.TimeoutException(e.message, e.cause)
    } catch (e: SocketTimeoutException) {
        throw NetworkException.TimeoutException(e.message, e.cause)
    } catch (e: HttpRequestTimeoutException) {
        throw NetworkException.TimeoutException(e.message, e.cause)
    }
    catch (e: Throwable) {
        // another errors
        throw UnknownException(e.message, e.cause)
    }
}

internal fun HttpRequestBuilder.prepareRequest(
    urlString: String,
    headers: Headers = headersOf(),
    queries: Parameters = parametersOf()
) {
    url(urlString)

    url.parameters.appendAll(queries)

    headers { appendAll(headers) }
    contentType(ContentType.Application.Json)

    expectSuccess = true
}