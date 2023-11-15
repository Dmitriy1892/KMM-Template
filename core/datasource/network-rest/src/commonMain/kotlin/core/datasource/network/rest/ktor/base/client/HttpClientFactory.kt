package core.datasource.network.rest.ktor.base.client

import io.github.dmitriy1892.kmm.utils.platform.Config
import core.datasource.network.rest.ktor.base.core.HttpEngineFactory
import core.datasource.network.rest.ktor.base.core.Interceptor
import core.datasource.network.rest.ktor.base.core.addInterceptor
import core.datasource.network.rest.ktor.base.core.installBaseUrl
import core.datasource.network.rest.ktor.base.core.installJsonSerializer
import core.datasource.network.rest.ktor.base.core.installLogger
import core.datasource.network.rest.ktor.base.core.installTimeouts
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig

object HttpClientFactory {

    fun create(
        baseUrl: String,
        engineFactory: HttpEngineFactory = HttpEngineFactory(),
        isNeedToLogging: Boolean = Config.isDebugBuild,
        interceptors: List<Interceptor> = emptyList(),
        configuration: HttpClientConfig<HttpClientEngineConfig>.() -> Unit = {
            installLogger(isNeedToLogging)
            installJsonSerializer()
            installTimeouts()
            installBaseUrl(baseUrl)
        }
    ): HttpClient = HttpClient(engineFactory.createEngine(), configuration).apply {
        interceptors.forEach { interceptor -> addInterceptor(interceptor) }
    }
}