package core.datasource.network.rest.ktor.base.client

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient

object KtorfitFactory {

    fun create(httpClient: HttpClient, baseUrl: String? = null): Ktorfit = Ktorfit.Builder()
        .httpClient(httpClient)
        .apply {
            baseUrl ?: return@apply
            this.baseUrl(baseUrl)
        }
        .build()
}