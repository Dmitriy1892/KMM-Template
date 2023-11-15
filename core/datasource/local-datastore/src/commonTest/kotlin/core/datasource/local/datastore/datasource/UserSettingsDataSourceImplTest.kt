package core.datasource.local.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import io.github.dmitriy1892.kmm.utils.coroutines.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okio.Path.Companion.toPath
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
class UserSettingsDataSourceImplTest {

    private val testDispatcher = newSingleThreadContext("UI test dispatcher")
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var source: UserSettingsDataSourceImpl

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        dataStore = PreferenceDataStoreFactory
            .createWithPath(produceFile = { "test_preferences.preferences_pb".toPath() })
        source = UserSettingsDataSourceImpl(
            object : CoroutineDispatcherProvider {
                override val default: CoroutineDispatcher = testDispatcher
                override val io: CoroutineDispatcher = default
                override val main: CoroutineDispatcher = default
                override val unconfined: CoroutineDispatcher = default
            },
            dataStore
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.close()
    }

    @Test
    fun testtt() = runTest {
        source.updateCounter(99)
        val out = source.getCounter()

        assertEquals(99, out)
    }
}