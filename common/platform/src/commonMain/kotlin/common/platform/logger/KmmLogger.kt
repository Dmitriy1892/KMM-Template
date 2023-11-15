package common.platform.logger

import io.github.dmitriy1892.kmm.utils.platform.Config
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier

object KmmLogger {

    internal var defaultTag: String = "LOGGER_LOG"
        private set

    fun initialize(
        isLoggingEnabled: Boolean = Config.isDebugBuild,
        defaultTag: String = this.defaultTag
    ) {
        Napier.initWithDebugAntilog(isLoggingEnabled, defaultTag)
    }

    fun verbose(tag: String, message: String, throwable: Throwable? = null): Unit =
        log(level = Level.VERBOSE, tag = tag, message = message, throwable = throwable)

    fun debug(tag: String, message: String, throwable: Throwable? = null): Unit =
        log(level = Level.DEBUG, tag = tag, message = message, throwable = throwable)

    fun info(tag: String, message: String, throwable: Throwable? = null): Unit =
        log(level = Level.INFO, tag = tag, message = message, throwable = throwable)

    fun warning(tag: String, message: String, throwable: Throwable? = null): Unit =
        log(level = Level.WARNING, tag = tag, message = message, throwable = throwable)

    fun error(tag: String, message: String, throwable: Throwable? = null): Unit =
        log(level = Level.ERROR, tag = tag, message = message, throwable = throwable)

    fun error(tag: String, throwable: Throwable): Unit =
        error(tag, throwable.message ?: "error", throwable)

    fun assert(tag: String, message: String, throwable: Throwable? = null): Unit =
        log(level = Level.ASSERT, tag = tag, message = message, throwable = throwable)

    fun v(tag: String, message: String, throwable: Throwable? = null): Unit =
        verbose(tag, message, throwable)

    fun d(tag: String, message: String, throwable: Throwable? = null): Unit =
        debug(tag, message, throwable)

    fun i(tag: String, message: String, throwable: Throwable? = null): Unit =
        info(tag, message, throwable)

    fun w(tag: String, message: String, throwable: Throwable? = null): Unit =
        warning(tag, message, throwable)

    fun e(tag: String, message: String, throwable: Throwable? = null): Unit =
        error(tag, message, throwable)

    fun e(tag: String, throwable: Throwable): Unit = error(tag, throwable)

    fun wtf(tag: String, message: String, throwable: Throwable? = null): Unit =
        assert(tag, message, throwable)

    private fun log(level: Level, tag: String?, message: String, throwable: Throwable?) {
        Napier.log(
            priority = level.asNapierLogLevel(),
            tag = tag,
            throwable = throwable,
            message = message
        )
    }

    enum class Level {
        VERBOSE,
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        ASSERT,
    }
}

private fun KmmLogger.Level.asNapierLogLevel(): LogLevel = when (this) {
    KmmLogger.Level.VERBOSE -> LogLevel.VERBOSE
    KmmLogger.Level.DEBUG -> LogLevel.DEBUG
    KmmLogger.Level.INFO -> LogLevel.INFO
    KmmLogger.Level.WARNING -> LogLevel.WARNING
    KmmLogger.Level.ERROR -> LogLevel.ERROR
    KmmLogger.Level.ASSERT -> LogLevel.ASSERT
}