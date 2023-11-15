package core.datasource.network.rest.service

import core.datasource.network.rest.model.response.ProductResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path

interface SampleService {

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductResponse
}