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
import common.decompose.navigation.NavGraph
import kotlinx.serialization.KSerializer
import navigation.impl.feature.tabview.ProfileRouterComponent

class ProfileGraph(
    componentContext: ComponentContext
) : NavGraph<ProfileGraphConfig, ProfileGraphDestination>(componentContext) {

    override fun getInitialConfig(): ProfileGraphConfig = ProfileGraphConfig.Profile(1)

    override fun getConfigSerializer(): KSerializer<ProfileGraphConfig> =
        ProfileGraphConfig.serializer()

    override fun createDestination(
        config: ProfileGraphConfig,
        componentContext: ComponentContext
    ): ProfileGraphDestination = when (config) {
        is ProfileGraphConfig.Profile -> ProfileGraphDestination.ProfileDestination(
            component = ProfileRouterComponent(componentContext, config.index, navigation)
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