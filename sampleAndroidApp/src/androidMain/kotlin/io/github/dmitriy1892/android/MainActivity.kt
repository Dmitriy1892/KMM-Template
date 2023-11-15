package io.github.dmitriy1892.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import core.datasource.local.database.datasource.SampleDataSource
import core.datasource.local.database.db.DbCreator
import core.datasource.local.database.db.DbDriverFactory
import core.datasource.network.rest.datasource.SampleNetworkDataSource
import feature.app.App
import feature.app.InitialDepsHolder
import io.github.dmitriy1892.kmm.utils.coroutines.dispatcher.CoroutineDispatcherProviderImpl
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.dsl.module

class MainActivity : ComponentActivity() {

    val sampleSource by lazy {
        SampleDataSource(
            DbCreator.getDatabase(
                DbDriverFactory(
                    PlatformConfiguration(this.applicationContext)
                )
            ).sampleTableQueries,
            CoroutineDispatcherProviderImpl()
        )
    }

    private val sampleService by inject<SampleNetworkDataSource>()

    private val initialDepsHolder: InitialDepsHolder by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        defineDefaultComponentContext()
        super.onCreate(savedInstanceState)


        lifecycleScope.launch {
            sampleSource.insert(1, "some")
        }

        sampleService.getProductInfo() // TODO - REMOVE

        setContent {
            App(initialDepsHolder = initialDepsHolder)
        }
    }

    private fun defineDefaultComponentContext() {
        getKoin().loadModules(
            modules = listOf(
                module { single<DefaultComponentContext> { defaultComponentContext() } }
            ),
            allowOverride = true
        )
    }
}
