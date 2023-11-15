package core.datasource.network.rest.ktor.base.core

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual class HttpEngineFactory actual constructor() {

   actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin
}