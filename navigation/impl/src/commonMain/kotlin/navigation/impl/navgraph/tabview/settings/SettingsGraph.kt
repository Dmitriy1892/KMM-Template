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
import common.decompose.navigation.NavGraph
import kotlinx.serialization.KSerializer
import navigation.impl.feature.tabview.SettingsRouterComponent

class SettingsGraph(
    componentContext: ComponentContext
) : NavGraph<SettingsGraphConfig, SettingsGraphDestination>(componentContext) {

    override fun getInitialConfig(): SettingsGraphConfig = SettingsGraphConfig.Settings(1)

    override fun getConfigSerializer(): KSerializer<SettingsGraphConfig> =
        SettingsGraphConfig.serializer()

    override fun createDestination(
        config: SettingsGraphConfig,
        componentContext: ComponentContext
    ): SettingsGraphDestination = when (config) {
        is SettingsGraphConfig.Settings -> SettingsGraphDestination.SettingsDestination(
            component = SettingsRouterComponent(componentContext, config.index, navigation)
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