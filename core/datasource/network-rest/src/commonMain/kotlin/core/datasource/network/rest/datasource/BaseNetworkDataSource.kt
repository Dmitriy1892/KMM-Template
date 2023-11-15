package core.datasource.network.rest.datasource

import core.data.datasource.BaseDataSource
import core.datasource.network.rest.ktor.base.wrapNetworkException
import core.domain.exception.NetworkException
import core.domain.exception.UnknownException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.cancellation.CancellationException

open class BaseNetworkDataSource(
    dispatcher: CoroutineDispatcher
) : BaseDataSource(dispatcher) {

    /**
     * Function for wrapping network api calls.
     *
     * @param block block of code for api calling logic
     *
     * @return [T]
     *
     * @throws[NetworkException] if non-200 response returned
     * @throws [UnknownException] if another exception appeared
     * @throws [CancellationException] if coroutine was cancelled
     */
    @Throws(NetworkException::class, UnknownException::class, CancellationException::class)
    suspend fun <T> apiCall(block: suspend CoroutineScope.() -> T): T = withDispatcher {
        wrapNetworkException { block() }
    }
}