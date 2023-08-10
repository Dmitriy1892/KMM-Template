package io.github.dmitriy1892.core.domain.exception

sealed class NetworkException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable(message, cause) {

    // 3xx error codes
    class RedirectException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException()

    // 401 error code
    class UnauthorizedException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException()

    // 404 error code
    class NotFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)

    // 4xx error codes
    class ClientSideException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)

    // 5xx error codes
    class ServerSideException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)

    class TimeoutException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)
}
