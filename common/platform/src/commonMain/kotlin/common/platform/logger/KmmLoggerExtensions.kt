package common.platform.logger

fun Any.logVerbose(message: String, throwable: Throwable? = null): Unit =
    KmmLogger.v(this.getTag(), message, throwable)

fun Any.logDebug(message: String, throwable: Throwable? = null): Unit =
    KmmLogger.d(this.getTag(), message, throwable)

fun Any.logInfo(message: String, throwable: Throwable? = null): Unit =
    KmmLogger.i(this.getTag(), message, throwable)

fun Any.logWarning(message: String, throwable: Throwable? = null): Unit =
    KmmLogger.w(this.getTag(), message, throwable)

fun Any.logError(message: String, throwable: Throwable? = null) =
    KmmLogger.e(this.getTag(), message, throwable)

fun Any.logError(throwable: Throwable) = KmmLogger.e(this.getTag(), throwable)

fun Any.logAssert(message: String, throwable: Throwable? = null) =
    KmmLogger.wtf(this.getTag(), message, throwable)

fun Any.logWtf(message: String, throwable: Throwable? = null) =
    KmmLogger.wtf(this.getTag(), message, throwable)

private fun Any.getTag(): String =
    this::class.simpleName?.ifBlank { KmmLogger.defaultTag } ?: KmmLogger.defaultTag