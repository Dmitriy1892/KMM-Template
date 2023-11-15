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
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import navigation.impl.BaseComponent
import navigation.impl.feature.tabview.AddRouterComponent

class AddGraph(
    componentContext: ComponentContext
) : BaseComponent(componentContext) {

    private val navigation = StackNavigation<AddGraphConfig>()
    private val childStack: Value<ChildStack<*, AddGraphDestination>> = childStack(
        source = navigation,
        serializer = AddGraphConfig.serializer(),
        initialConfiguration = AddGraphConfig.AddScreen(1),
        handleBackButton = true,
        childFactory = ::createDestination
    )

    @Composable
    override fun Content() {
        Children(
            modifier = Modifier.fillMaxSize(),
            stack = childStack,
            animation = stackAnimation(fade() + scale())
        ) { child ->
            child.instance.component.ContentWithLifecycle()
        }
    }

    private fun createDestination(
        config: AddGraphConfig,
        context: ComponentContext
    ): AddGraphDestination = when (config) {
        is AddGraphConfig.AddScreen -> AddGraphDestination.Add(
            component = AddRouterComponent(context, config.index, navigation)
        )
    }
}