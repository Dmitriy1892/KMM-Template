
package sample.feature.tabview.tabs.settings

import io.github.dmitriy1892.kmm.mvi.kmm.mvvm.MviViewModel
import io.github.dmitriy1892.kmm.mvvm.core.BaseViewModel
import io.github.dmitriy1892.kmm.mvi.core.extensions.intent
import io.github.dmitriy1892.kmm.mvvm.core.VIEW_MODEL_KEY_PREFIX
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Named
import sample.feature.tabview.tabs.settings.model.SettingsSideEffect
import sample.feature.tabview.tabs.settings.model.SettingsState

@Factory(binds = [BaseViewModel::class])
@Named(value = "$VIEW_MODEL_KEY_PREFIX:sample.feature.tabview.tabs.settings.SettingsViewModel")
class SettingsViewModel(
    @InjectedParam private val screenIndex: Int
) : MviViewModel<SettingsState, SettingsSideEffect>() {

    override val initialState: SettingsState = SettingsState(screenIndex = screenIndex)

    fun openNextScreen() = intent {
        sendSideEffect(SettingsSideEffect.OpenNextScreen)
    }
}