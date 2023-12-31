package sample.feature.tabview.tabs.profile.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectAsStateWithLifecycle
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectSideEffectWithLifecycle
import io.github.dmitriy1892.kmm.mvvm.compose.kmmViewModel
import io.github.dmitriy1892.kmm.mvvm.koin.factory.KoinAssistedViewModelFactory
import org.koin.core.parameter.parametersOf
import sample.feature.tabview.tabs.profile.ProfileViewModel
import sample.feature.tabview.tabs.profile.model.ProfileSideEffect
import sample.feature.tabview.tabs.profile.model.ProfileState

@Composable
fun ProfileScreen(
    screenIndex: Int,
    viewModelKey: String? = null,
    viewModel: ProfileViewModel = kmmViewModel(
        key = viewModelKey,
        factory = KoinAssistedViewModelFactory(parametersOf(screenIndex))
    ),
    navigateToNextScreen: () -> Unit
) {
    println("KMM_VM: ProfileViewModel: index: $screenIndex key: $viewModelKey, state = $viewModel, sideEffect = ${viewModel.sideEffectFlow}")
    viewModel.sideEffectFlow.collectSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            ProfileSideEffect.OpenNextScreen -> navigateToNextScreen()
        }
    }

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    ProfileScreenContent(
        state = state,
        onClick = remember {{ viewModel.openNextScreen() }}
    )
}

@Composable
fun ProfileScreenContent(
    state: ProfileState,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = "PROFILE SCREEN #${state.screenIndex}")

        Button(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 40.dp),
            enabled = state.isProgress.not(),
            onClick = onClick
        ) {
            Text(text = "Open next")
        }

        if (state.isProgress) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}