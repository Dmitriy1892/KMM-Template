package common.platform.logger

import io.github.dmitriy1892.kmm.utils.platform.Config
import io.github.aakira.napier.Antilog
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier

internal fun Napier.initWithDebugAntilog(
    isEnabled: Boolean = Config.isDebugBuild,
    defaultTag: String = "Napier"
) {
    val antilog = if (isEnabled) DebugAntilog(defaultTag) else EmptyAntilog
    this.base(antilog)
}

private object EmptyAntilog : Antilog() {

    override fun performLog(
        priority: LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String?
    ) = Unit

    override fun isEnable(priority: LogLevel, tag: String?): Boolean = false
}