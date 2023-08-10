package io.github.dmitriy1892.di.impl.modules

import io.github.dmitriy1892.core.datasource.network.ktor.NetworkClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.github.dmitriy1892.core.datasource.network.ktor.CredentialsManager
import io.github.dmitriy1892.core.datasource.network.ktor.HttpEngineFactory
import io.github.dmitriy1892.core.datasource.network.ktor.LoggingSettings
import io.github.dmitriy1892.di.impl.CredentialsManagerImpl
import io.github.dmitriy1892.kmm.utils.platform.Config
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkDataSourceModule = module {

    single<CredentialsManager> { CredentialsManagerImpl() }

    single(named(BASE_URL)) { if (Config.isDebugBuild) DEV_URL else PROD_URL }

    single<HttpClientEngineFactory<HttpClientEngineConfig>> { HttpEngineFactory().createEngine() }

    single<LoggingSettings> { LoggingSettings() }

    single<HttpClient> {
        NetworkClient(
            baseUrl = get<String>(named(BASE_URL)),
            engine = get<HttpClientEngineFactory<HttpClientEngineConfig>>(),
            loggingSettings = get<LoggingSettings>(),
            credentialsManager = get<CredentialsManager>()
        ).client
    }
}

const val BASE_URL = "BASE_URL"

val PROD_URL = "https://sample.url"
val DEV_URL = "https://sample-dev.url"