
package sample.feature.tabview.tabs.profile

import io.github.dmitriy1892.kmm.mvi.kmm.mvvm.MviViewModel
import io.github.dmitriy1892.kmm.mvvm.core.BaseViewModel
import io.github.dmitriy1892.kmm.mvi.core.extensions.intent
import io.github.dmitriy1892.kmm.mvvm.core.VIEW_MODEL_KEY_PREFIX
import kotlinx.coroutines.delay
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Named
import sample.feature.tabview.tabs.profile.model.ProfileSideEffect
import sample.feature.tabview.tabs.profile.model.ProfileState

@Factory(binds = [BaseViewModel::class])
@Named(value = "$VIEW_MODEL_KEY_PREFIX:sample.feature.tabview.tabs.profile.ProfileViewModel")
class ProfileViewModel(
    @InjectedParam private val screenIndex: Int
) : MviViewModel<ProfileState, ProfileSideEffect>() {

    override val initialState: ProfileState = ProfileState(screenIndex = screenIndex)

    fun openNextScreen() = intent {
        reduceState { state -> state.copy(isProgress = true) }
        println("KMM_MVI: ProfileViewModel before delay")
        delay(3000)
        println("KMM_MVI: ProfileViewModel after delay - try send event")
        sendSideEffect(ProfileSideEffect.OpenNextScreen)
        println("KMM_MVI: ProfileViewModel after delay - successful send event")
        reduceState { state -> state.copy(isProgress = false) }
    }
}