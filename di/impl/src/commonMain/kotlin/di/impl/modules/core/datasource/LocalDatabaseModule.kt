package di.impl.modules.core.datasource

import core.datasource.local.database.LocalDatabaseDataSourceModule
import core.datasource.local.database.db.DbCreator
import core.datasource.local.database.generated.Database
import org.koin.dsl.module
import org.koin.ksp.generated.module
import tables.SampleTableQueries

val localDatabaseModule = module {
    includes(LocalDatabaseDataSourceModule().module)

    single<Database> { DbCreator.getDatabase(driverFactory = get()) }

    single<SampleTableQueries> { get<Database>().sampleTableQueries }
}