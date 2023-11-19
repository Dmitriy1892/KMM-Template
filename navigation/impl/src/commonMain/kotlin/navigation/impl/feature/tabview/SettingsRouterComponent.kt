package navigation.impl.feature.tabview

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import common.decompose.navigation.Destination
import navigation.api.feature.tabview.SettingsRouter
import sample.feature.tabview.tabs.settings.view.SettingsScreen
import navigation.impl.navgraph.tabview.settings.SettingsGraphConfig

class SettingsRouterComponent(
    componentContext: ComponentContext,
    private val screenIndex: Int,
    private val navigation: StackNavigation<SettingsGraphConfig>
) : Destination(
    componentContext = componentContext,
    viewModelKey = "settingsKey_$screenIndex"
), SettingsRouter {

    override fun openNextScreen() {
        if (screenIndex >= 3) {
            navigation.popTo(0)
        } else {
            navigation.push(SettingsGraphConfig.Settings(screenIndex + 1))
        }
    }

    @Composable
    override fun ContentScreen() {
        SettingsScreen(
            screenIndex = screenIndex,
            viewModelKey = viewModelKey,
            navigateToNextScreen = ::openNextScreen
        )
    }
}