package di.impl.modules.core

import core.data.CoreDataModule
import core.domain.CoreDomainModule
import di.impl.modules.core.datasource.localDataStoreModule
import di.impl.modules.core.datasource.localDatabaseModule
import di.impl.modules.core.datasource.networkDataSourceModule
import org.koin.dsl.module
import org.koin.ksp.generated.module

val coreModule = module {
    includes(
        localDatabaseModule,
        localDataStoreModule,
        networkDataSourceModule,
        CoreDataModule().module,
        CoreDomainModule().module
    )
}