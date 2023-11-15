package core.datasource.local.datastore.instance

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import okio.Path

object LocalDataStoreFactory {

    fun create(
        fileName: String,
        platformConfiguration: PlatformConfiguration
    ): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
        corruptionHandler = null,
        migrations = emptyList(),
        produceFile = { produceDirPath(fileName, platformConfiguration) }
    )
}

internal expect fun produceDirPath(
    fileName: String,
    platformConfiguration: PlatformConfiguration
): Path