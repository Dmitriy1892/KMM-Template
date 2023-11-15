package navigation.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.ComponentContext
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.LocalLifecycleOwner
import org.koin.core.component.KoinComponent

abstract class BaseComponent(
    componentContext: ComponentContext
) : KoinComponent, ComponentContext by componentContext {

    @Composable
    fun ContentWithLifecycle() {
        CompositionLocalProvider(LocalLifecycleOwner provides this) {
            Content()
        }
    }

    @Composable
    protected abstract fun Content()
}