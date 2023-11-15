package core.datasource.network.rest.datasource

import common.platform.logger.logDebug
import common.platform.logger.logError
import core.datasource.network.rest.service.SampleService
import di.api.APP_COROUTINE_SCOPE
import io.github.dmitriy1892.kmm.utils.coroutines.dispatcher.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single(binds = [SampleNetworkDataSource::class])
class SampleNetworkDataSourceImpl(
    private val sampleService: SampleService,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    @Named(APP_COROUTINE_SCOPE) private val appScope: CoroutineScope
) : BaseNetworkDataSource(coroutineDispatcherProvider.io), SampleNetworkDataSource {

    override fun getProductInfo() {
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            logError(throwable)
        }
        val context = coroutineDispatcherProvider.io + errorHandler
        appScope.launch(context) {
            apiCall {
                val response = sampleService.getProductById(1)

                this@SampleNetworkDataSourceImpl.logDebug(response.toString())
            }
        }
    }
}

interface SampleNetworkDataSource {

    fun getProductInfo()
}