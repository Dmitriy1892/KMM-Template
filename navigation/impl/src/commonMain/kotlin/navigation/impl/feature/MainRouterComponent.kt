package navigation.impl.feature

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import common.decompose.navigation.Destination
import navigation.api.feature.MainRouter
import navigation.impl.navgraph.root.RootConfig
import sample.feature.main.ui.MainScreen

class MainRouterComponent(
     componentContext: ComponentContext,
     private val isFirstScreen: Boolean,
     private val navigation: StackNavigation<RootConfig>
) : Destination(
    componentContext = componentContext,
    viewModelKey = if (isFirstScreen) "MainVm1" else "MainVm2"
), MainRouter {

    override fun openMainScreen() {
        navigation.push(RootConfig.TabsConfig)
    }

    override fun openSampleOneScreen(id: Int) {
        navigation.push(RootConfig.SampleOneConfig(id))
    }

    @Composable
    override fun ContentScreen() {
        MainScreen(
            isFirstScreen = isFirstScreen,
            navigateUpToMain = ::openMainScreen,
            navigateToSampleOne = ::openSampleOneScreen
        )
    }
}