package feature.app

import androidx.compose.runtime.Composable

@Composable
expect fun App(
    initialDepsHolder: InitialDepsHolder
)