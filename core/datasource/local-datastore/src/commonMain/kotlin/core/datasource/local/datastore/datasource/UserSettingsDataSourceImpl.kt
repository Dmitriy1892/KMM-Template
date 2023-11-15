package core.datasource.local.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import core.data.datasource.BaseDataSource
import io.github.dmitriy1892.kmm.utils.coroutines.dispatcher.CoroutineDispatcherProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import org.koin.core.annotation.Single

@Single(binds = [UserSettingsDataSource::class])
class UserSettingsDataSourceImpl(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val dataStore: DataStore<Preferences>
) : BaseDataSource(coroutineDispatcherProvider.io), UserSettingsDataSource {

    private val counter by lazy { intPreferencesKey("counter") }

    override suspend fun updateCounter(newValue: Int): Unit = withDispatcher {
        dataStore.edit { prefs ->
            prefs[counter] = newValue
        }
    }

    override suspend fun getCounter(): Int = withDispatcher {
        dataStore.data
            .mapNotNull { prefs -> prefs[counter] }
            .first()
    }
}

interface UserSettingsDataSource {

    suspend fun updateCounter(newValue: Int)

    suspend fun getCounter(): Int
}