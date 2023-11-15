package core.datasource.local.database.datasource

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneNotNull
import core.data.datasource.BaseDataSource
import io.github.dmitriy1892.kmm.utils.coroutines.dispatcher.CoroutineDispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import tables.SampleTable
import tables.SampleTableQueries

@Single
class SampleDataSource(
    private val sampleTableQueries: SampleTableQueries,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : BaseDataSource(coroutineDispatcherProvider.io) {

    suspend fun insert(id: Long, someField: String) = withDispatcher {
        sampleTableQueries.insert(SampleTable(id, someField))
    }

    fun getSomeFieldById(id: Long): Flow<String> =
        sampleTableQueries
            .getById(id)
            .asFlow()
            .mapToOneNotNull(coroutineDispatcherProvider.io)
            .map { it.some_field }
            .flowOn(coroutineDispatcherProvider.io)
}