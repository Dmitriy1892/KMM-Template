package feature.app

import androidx.compose.runtime.Composable
import io.github.dmitriy1892.kmm.mvvm.compose.ComposeViewModelApp
import resources.uikit.theme.AppTheme

@Composable
actual fun App(
    initialDepsHolder: InitialDepsHolder
) {
    AppTheme {
        ComposeViewModelApp(
            viewModelStore = initialDepsHolder.viewModelStore,
            viewModelFactory = initialDepsHolder.viewModelFactory,
        ) {
            initialDepsHolder.rootComponent.Content()
        }
    }
}