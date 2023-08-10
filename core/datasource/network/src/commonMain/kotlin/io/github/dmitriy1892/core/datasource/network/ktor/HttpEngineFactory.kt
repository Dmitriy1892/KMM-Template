package io.github.dmitriy1892.core.datasource.network.ktor

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect class HttpEngineFactory constructor() {

    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}