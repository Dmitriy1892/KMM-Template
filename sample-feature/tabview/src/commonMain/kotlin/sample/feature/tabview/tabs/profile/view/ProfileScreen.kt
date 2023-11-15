package sample.feature.tabview.tabs.profile.view

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
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectAsStateWithEssentyLifecycle
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectSideEffect
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectSideEffectWithEssentyLifecycle
import io.github.dmitriy1892.kmm.mvvm.compose.kmmViewModel
import io.github.dmitriy1892.kmm.mvvm.koin.factory.KoinAssistedViewModelFactory
import org.koin.core.parameter.parametersOf
import sample.feature.tabview.tabs.profile.model.ProfileSideEffect
import sample.feature.tabview.tabs.profile.model.ProfileState
import sample.feature.tabview.tabs.profile.ProfileViewModel

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
    viewModel.sideEffectFlow.collectSideEffectWithEssentyLifecycle { sideEffect ->
        when (sideEffect) {
            ProfileSideEffect.OpenNextScreen -> navigateToNextScreen()
        }
    }

    val state by viewModel.stateFlow.collectAsStateWithEssentyLifecycle()

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
            onClick = onClick
        ) {
            Text(text = "Open next")
        }
    }
}