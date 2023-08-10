package io.github.dmitriy1892.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.dmitriy1892.kmm.utils.coroutines.CoroutineDispatcherProviderImpl
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import io.github.dmitriy1892.core.datasource.local.database.datasource.SampleDataSource
import io.github.dmitriy1892.core.datasource.local.database.db.DbCreator
import io.github.dmitriy1892.core.datasource.local.database.db.DbDriverFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val state by sampleSource.getSomeFieldById(1).collectAsState(initial = "none")
                    GreetingView(state)

                    LaunchedEffect(key1 = "one", block = {
                        withContext(Dispatchers.IO) {
                            repeat(10) {
                                delay(1000)
                                sampleSource.insert(1, "Value: $it")
                            }
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
