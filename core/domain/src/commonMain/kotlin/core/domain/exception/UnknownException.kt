package core.domain.exception

class UnknownException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable(message, cause)