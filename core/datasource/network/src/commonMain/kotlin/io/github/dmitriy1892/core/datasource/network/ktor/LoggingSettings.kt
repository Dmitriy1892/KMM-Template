package io.github.dmitriy1892.core.datasource.network.ktor

import io.github.dmitriy1892.kmm.utils.platform.Config

class LoggingSettings {
    val isNeedToLogging: Boolean = Config.isDebugBuild
}