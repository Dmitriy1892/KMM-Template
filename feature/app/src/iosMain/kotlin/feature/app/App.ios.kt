package feature.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.dmitriy1892.kmm.mvvm.compose.ComposeViewModelApp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.jetbrains.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import resources.uikit.theme.AppTheme

@OptIn(ExperimentalDecomposeApi::class)
@Composable
actual fun App(
    initialDepsHolder: InitialDepsHolder
) {
    AppTheme {
        PredictiveBackGestureOverlay(
            backDispatcher = initialDepsHolder.backDispatcher,
            backIcon = { progress, _ ->
                PredictiveBackGestureIcon(
                    imageVector = Icons.Default.ArrowBack,
                    progress = progress
                )
            },
            modifier = Modifier.fillMaxSize()
        ) {
            ComposeViewModelApp(
                viewModelStore = initialDepsHolder.viewModelStore,
                viewModelFactory = initialDepsHolder.viewModelFactory
            ) {
                initialDepsHolder.rootComponent.Content()
            }
        }
    }
}

interface RootComponentIos {

    val backDispatcher: BackDispatcher

    @Composable
    fun Content()
}