package di.impl

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import io.github.dmitriy1892.kmm.utils.coroutines.CoroutineDispatcherProvider
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.stack.StackNavigation
import core.datasource.network.rest.ktor.CredentialsManager
import di.impl.modules.mainModule
import kotlinx.coroutines.CoroutineScope
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.Verify
import org.koin.test.verify.verifyAll

class KoinGraphValidationTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun validateKoinMainGraph() {
        Verify.addExtraTypes(

        )
        mainModule.verifyAll(
            extraTypes = listOf(
                SqlDriver::class,
                CoroutineDispatcherProvider::class,
                PlatformConfiguration::class,
                CoroutineScope::class,
                CredentialsManager::class,
                Context::class,
                DefaultComponentContext::class,
            )
        )
    }
}