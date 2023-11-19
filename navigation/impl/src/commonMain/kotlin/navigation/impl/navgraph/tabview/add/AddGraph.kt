package navigation.impl.navgraph.tabview.add

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import common.decompose.navigation.NavGraph
import kotlinx.serialization.KSerializer
import navigation.impl.feature.tabview.AddRouterComponent

class AddGraph(
    componentContext: ComponentContext
) : NavGraph<AddGraphConfig, AddGraphDestination>(componentContext) {

    override fun getInitialConfig(): AddGraphConfig = AddGraphConfig.AddScreen(1)

    override fun getConfigSerializer(): KSerializer<AddGraphConfig> = AddGraphConfig.serializer()

    override fun createDestination(
        config: AddGraphConfig,
        componentContext: ComponentContext
    ): AddGraphDestination = when (config) {
        is AddGraphConfig.AddScreen -> AddGraphDestination.Add(
            component = AddRouterComponent(componentContext, config.index, navigation)
        )
    }

    @Composable
    override fun ContentScreen() {
        Children(
            modifier = Modifier.fillMaxSize(),
            stack = childStack,
            animation = stackAnimation(fade() + scale())
        ) { child ->
            child.instance.component.Content()
        }
    }
}