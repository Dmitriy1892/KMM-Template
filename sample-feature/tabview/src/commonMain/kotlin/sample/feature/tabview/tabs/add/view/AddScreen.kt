package sample.feature.tabview.tabs.add.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectAsStateWithLifecycle
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectSideEffect
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectSideEffectWithLifecycle
import io.github.dmitriy1892.kmm.mvvm.compose.kmmViewModel
import io.github.dmitriy1892.kmm.mvvm.koin.factory.KoinAssistedViewModelFactory
import org.koin.core.parameter.parametersOf
import sample.feature.tabview.tabs.add.model.AddSideEffect
import sample.feature.tabview.tabs.add.model.AddState
import sample.feature.tabview.tabs.add.AddViewModel

@Composable
fun AddScreen(
    screenIndex: Int,
    viewModelKey: String? = null,
    viewModel: AddViewModel = kmmViewModel(
        key = viewModelKey,
        factory = KoinAssistedViewModelFactory(parametersOf(screenIndex))
    ),
    navigateToNextScreen: () -> Unit
) {
    viewModel.sideEffectFlow.collectSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is AddSideEffect.OpenNextScreen -> navigateToNextScreen()
        }
    }

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    AddScreenContent(
        state = state,
        onClick = remember {{ viewModel.openNextScreen() }}
    )
}

@Composable
fun AddScreenContent(
    state: AddState,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = "ADD SCREEN #${state.screenIndex}")

        Button(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 40.dp),
            onClick = onClick
        ) {
            Text(text = "Open next")
        }
    }
}