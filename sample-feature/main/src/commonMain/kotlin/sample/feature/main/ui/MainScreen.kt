package sample.feature.main.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectAsStateWithLifecycle
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectSideEffectWithLifecycle
import io.github.dmitriy1892.kmm.mvvm.compose.kmmViewModel
import io.github.dmitriy1892.kmm.utils.platform.Config

@Composable
fun MainScreen(
    isFirstScreen: Boolean,
    navigateToSampleOne: (id: Int) -> Unit,
    navigateUpToMain: () -> Unit,
    viewModel: MainViewModel = kmmViewModel(key = if (isFirstScreen) "MainVm1" else "MainVm2")
) {
    val state: MainState by viewModel.stateFlow.collectAsStateWithLifecycle()

    viewModel.sideEffectFlow.collectSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is MainSideEffect.OpenSampleOneScreen -> navigateToSampleOne(sideEffect.id)
        }
    }

    MainScreenContent(
        screenName = if (isFirstScreen) "Screen 1" else "Screen 3",
        state = state,
        onClick = remember {{
            if (isFirstScreen) viewModel.createSomeId() else navigateUpToMain()
        }}
    )
}

@Composable
fun MainScreenContent(
    screenName: String,
    state: MainState,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "IsDebug: ${Config.isDebugBuild}")
        Text(text = screenName)
        Text(text = state.someText)
        Button(
            onClick = onClick
        ) {
            Text(text = "Go next")
        }
    }
}