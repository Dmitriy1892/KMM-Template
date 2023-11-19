package navigation.impl.feature.tabview

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import common.decompose.navigation.Destination
import sample.feature.tabview.tabs.profile.view.ProfileScreen
import navigation.api.feature.tabview.ProfileRouter
import navigation.impl.navgraph.tabview.profile.ProfileGraphConfig

class ProfileRouterComponent(
    componentContext: ComponentContext,
    private val screenIndex: Int,
    private val navigation: StackNavigation<ProfileGraphConfig>
) : Destination(
    componentContext = componentContext,
    viewModelKey = "profileScreen_$screenIndex"
), ProfileRouter {

    override fun openNextScreen() {
        if (screenIndex >= 3) {
            navigation.popTo(0)
        } else {
            navigation.push(ProfileGraphConfig.Profile(screenIndex + 1))
        }
    }

    @Composable
    override fun ContentScreen() {
        ProfileScreen(
            screenIndex = screenIndex,
            viewModelKey = viewModelKey,
            navigateToNextScreen = ::openNextScreen
        )
    }
}