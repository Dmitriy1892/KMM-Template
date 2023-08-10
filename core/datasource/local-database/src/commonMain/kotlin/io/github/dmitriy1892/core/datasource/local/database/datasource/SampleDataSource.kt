package io.github.dmitriy1892.core.datasource.local.database.datasource

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOneNotNull
import io.github.dmitriy1892.kmm.utils.coroutines.CoroutineDispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import tables.SampleTable
import tables.SampleTableQueries

class SampleDataSource(
    private val sampleTableQueries: SampleTableQueries,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) {

    suspend fun insert(id: Long, someField: String) = withContext(coroutineDispatcherProvider.io) {
        sampleTableQueries.insert(SampleTable(id, someField))
    }

    fun getSomeFieldById(id: Long): Flow<String> =
        sampleTableQueries
            .getById(id)
            .asFlow()
            .mapToOneNotNull()
            .map { it.some_field }
            .flowOn(coroutineDispatcherProvider.io)
}