package di.impl.modules.core.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import core.datasource.local.datastore.LocalDataStoreDataSourceModule
import core.datasource.local.datastore.instance.LocalDataStoreFactory
import org.koin.dsl.module
import org.koin.ksp.generated.module

val localDataStoreModule = module {
    includes(LocalDataStoreDataSourceModule().module)

    single<DataStore<Preferences>> {
        LocalDataStoreFactory.create(
            fileName = "datastore.preferences_pb",
            platformConfiguration = get<PlatformConfiguration>()
        )
    }
}