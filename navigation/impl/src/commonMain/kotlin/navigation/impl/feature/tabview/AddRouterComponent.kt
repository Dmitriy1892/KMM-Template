package navigation.impl.feature.tabview

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import common.decompose.navigation.Destination
import sample.feature.tabview.tabs.add.view.AddScreen
import navigation.api.feature.tabview.AddRouter
import navigation.impl.navgraph.tabview.add.AddGraphConfig

class AddRouterComponent(
    componentContext: ComponentContext,
    private val screenIndex: Int,
    private val navigation: StackNavigation<AddGraphConfig>
) : Destination(
    componentContext = componentContext,
    viewModelKey = "addScreen_$screenIndex"
), AddRouter {

    override fun openNextScreen() {
        if (screenIndex >= 3) {
            navigation.popTo(0)
        } else {
            navigation.push(AddGraphConfig.AddScreen(screenIndex + 1))
        }
    }

    @Composable
    override fun ContentScreen() {
        AddScreen(
            screenIndex = screenIndex,
            viewModelKey = viewModelKey,
            navigateToNextScreen = ::openNextScreen
        )
    }
}