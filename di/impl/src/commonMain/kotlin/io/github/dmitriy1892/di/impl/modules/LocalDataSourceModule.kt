package io.github.dmitriy1892.di.impl.modules

import io.github.dmitriy1892.core.datasource.local.database.Database
import io.github.dmitriy1892.core.datasource.local.database.datasource.SampleDataSource
import io.github.dmitriy1892.core.datasource.local.database.db.DbCreator
import io.github.dmitriy1892.core.datasource.local.database.db.DbDriverFactory
import org.koin.dsl.module
import tables.SampleTableQueries

val localDataSourceModule = module {
    single<DbDriverFactory> { DbDriverFactory(platformConfiguration = get()) }

    single<Database> { DbCreator.getDatabase(driverFactory = get()) }

    single<SampleDataSource> {
        val queries: SampleTableQueries = get<Database>().sampleTableQueries
        SampleDataSource(sampleTableQueries = queries, coroutineDispatcherProvider = get())
    }
}