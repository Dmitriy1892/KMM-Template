package navigation.impl.navgraph.tabview.settings

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
import navigation.impl.feature.tabview.SettingsRouterComponent
import navigation.impl.navgraph.tabview.profile.ProfileGraphConfig
import navigation.impl.navgraph.tabview.profile.ProfileGraphDestination

class SettingsGraph(componentContext: ComponentContext) : BaseComponent(componentContext) {

    private val navigation = StackNavigation<SettingsGraphConfig>()
    private val childStack: Value<ChildStack<*, SettingsGraphDestination>> = childStack(
        source = navigation,
        serializer = SettingsGraphConfig.serializer(),
        initialConfiguration = SettingsGraphConfig.Settings(1),
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
        config: SettingsGraphConfig,
        context: ComponentContext
    ): SettingsGraphDestination = when (config) {
        is SettingsGraphConfig.Settings -> SettingsGraphDestination.SettingsDestination(
            component = SettingsRouterComponent(context, config.index, navigation)
        )
    }
}