package core.data.datasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

open class BaseDataSource(private val dispatcher: CoroutineDispatcher) {

    /**
     * Call suspend function with dispatcher that defined in constructor.
     *
     * @param block block of code for suspend operation
     *
     * @return [T]
     */
    suspend fun <T> withDispatcher(
        block: suspend CoroutineScope.() -> T
    ): T = withContext(dispatcher) {
        block()
    }
}