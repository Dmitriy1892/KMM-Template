package core.datasource.network.rest.ktor.client

import core.datasource.network.rest.ktor.base.BASE_URL_QUALIFIER
import core.datasource.network.rest.ktor.base.client.HttpClientFactory
import core.datasource.network.rest.ktor.base.client.KtorfitFactory
import core.datasource.network.rest.ktor.interceptor.AuthTokenInterceptor
import core.datasource.network.rest.ktor.interceptor.RefreshTokenInterceptor
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class SampleNetworkClient(
    @Named(BASE_URL_QUALIFIER) private val baseUrl: String,
    private val authTokenInterceptor: AuthTokenInterceptor,
    private val refreshTokenInterceptor: RefreshTokenInterceptor
) {

    val ktorfit: Ktorfit by lazy { KtorfitFactory.create(createClient()) }

    private fun createClient(): HttpClient = HttpClientFactory.create(
        baseUrl = baseUrl,
        interceptors = listOf(
            authTokenInterceptor,
            refreshTokenInterceptor
        )
    )
}