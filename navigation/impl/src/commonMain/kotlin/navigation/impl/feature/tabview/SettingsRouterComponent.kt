package navigation.impl.feature.tabview

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import navigation.api.feature.tabview.SettingsRouter
import sample.feature.tabview.tabs.settings.view.SettingsScreen
import navigation.impl.BaseDestinationComponent
import navigation.impl.navgraph.tabview.settings.SettingsGraphConfig

class SettingsRouterComponent(
    componentContext: ComponentContext,
    private val screenIndex: Int,
    private val navigation: StackNavigation<SettingsGraphConfig>
) : BaseDestinationComponent(componentContext), SettingsRouter {

    override val viewModelKey: String = "settingsKey_$screenIndex"

    override fun openNextScreen() {
        if (screenIndex >= 3) {
            navigation.popTo(0)
        } else {
            navigation.push(SettingsGraphConfig.Settings(screenIndex + 1))
        }
    }

    @Composable
    override fun Content() {
        SettingsScreen(
            screenIndex = screenIndex,
            viewModelKey = viewModelKey,
            navigateToNextScreen = ::openNextScreen
        )
    }
}