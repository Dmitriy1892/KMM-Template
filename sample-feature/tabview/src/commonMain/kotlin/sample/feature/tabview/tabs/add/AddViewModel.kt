package sample.feature.tabview.tabs.add

import io.github.dmitriy1892.kmm.mvi.kmm.mvvm.MviViewModel
import io.github.dmitriy1892.kmm.mvvm.core.BaseViewModel
import io.github.dmitriy1892.kmm.mvi.core.extensions.intent
import io.github.dmitriy1892.kmm.mvvm.core.VIEW_MODEL_KEY_PREFIX
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Named
import sample.feature.tabview.tabs.add.model.AddSideEffect
import sample.feature.tabview.tabs.add.model.AddState

@Factory(binds = [BaseViewModel::class])
@Named(value = "$VIEW_MODEL_KEY_PREFIX:sample.feature.tabview.tabs.add.AddViewModel")
class AddViewModel(
    @InjectedParam private val screenIndex: Int
) : MviViewModel<AddState, AddSideEffect>() {

    override val initialState: AddState = AddState(screenIndex = screenIndex)

    fun openNextScreen() = intent {
        sendSideEffect(AddSideEffect.OpenNextScreen)
    }
}