package sample.feature.main.ui

import io.github.dmitriy1892.kmm.mvi.kmm.mvvm.MviViewModel
import io.github.dmitriy1892.kmm.mvvm.core.BaseViewModel
import io.github.dmitriy1892.kmm.mvvm.core.VIEW_MODEL_KEY_PREFIX
import io.github.dmitriy1892.kmm.mvi.core.extensions.intent
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import kotlin.random.Random

@Factory(binds = [BaseViewModel::class])
@Named(value = "$VIEW_MODEL_KEY_PREFIX:sample.feature.main.ui.MainViewModel")
class MainViewModel : MviViewModel<MainState, MainSideEffect>() {

    override val initialState: MainState = MainState(
        someText = "Random: ${Random.nextInt(1, 1000)}"
    )

    fun createSomeId() = intent {
        val id = Random.nextInt(1, 1000)
        sendSideEffect(MainSideEffect.OpenSampleOneScreen(id))
    }
}