package common.decompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.LocalLifecycleOwner
import io.github.dmitriy1892.kmm.mvvm.core.store.ViewModelStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class Destination(
    componentContext: ComponentContext,
    protected val viewModelKey: String
) : KoinComponent, ComponentContext by componentContext {

    private val viewModelStore: ViewModelStore by inject()

    init {
        keepViewModelInstance()
    }

    @Composable
    protected abstract fun ContentScreen()

    @Composable
    fun Content() {
        CompositionLocalProvider(LocalLifecycleOwner provides this) {
            ContentScreen()
        }
    }

    private fun keepViewModelInstance() {
        instanceKeeper.getOrCreate {
            ViewModelInstanceKeeper(viewModelStore, viewModelKey)
        }
    }
}