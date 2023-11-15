package navigation.impl.navgraph.tabview.profile

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
import navigation.impl.feature.tabview.ProfileRouterComponent

class ProfileGraph(
    componentContext: ComponentContext
) : BaseComponent(componentContext) {

    private val navigation = StackNavigation<ProfileGraphConfig>()
    private val childStack: Value<ChildStack<*, ProfileGraphDestination>> = childStack(
        source = navigation,
        serializer = ProfileGraphConfig.serializer(),
        initialConfiguration = ProfileGraphConfig.Profile(1),
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
        config: ProfileGraphConfig,
        context: ComponentContext
    ): ProfileGraphDestination = when (config) {
        is ProfileGraphConfig.Profile -> ProfileGraphDestination.ProfileDestination(
            component = ProfileRouterComponent(context, config.index, navigation)
        )
    }
}