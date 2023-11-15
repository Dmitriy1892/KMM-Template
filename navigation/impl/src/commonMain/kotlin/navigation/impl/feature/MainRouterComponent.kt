package navigation.impl.feature

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import sample.feature.main.ui.MainScreen
import navigation.api.feature.MainRouter
import navigation.impl.BaseDestinationComponent
import navigation.impl.navgraph.root.RootConfig

class MainRouterComponent(
     componentContext: ComponentContext,
     private val isFirstScreen: Boolean,
     private val navigation: StackNavigation<RootConfig>
) : BaseDestinationComponent(componentContext), MainRouter {

    override val viewModelKey: String = if (isFirstScreen) "MainVm1" else "MainVm2"

    override fun openMainScreen() {
        navigation.push(RootConfig.TabsConfig)
    }

    override fun openSampleOneScreen(id: Int) {
        navigation.push(RootConfig.SampleOneConfig(id))
    }

    @Composable
    override fun Content() {
        MainScreen(
            isFirstScreen = isFirstScreen,
            navigateUpToMain = ::openMainScreen,
            navigateToSampleOne = ::openSampleOneScreen
        )
    }
}