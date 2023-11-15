package navigation.impl.navgraph.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import feature.app.RootComponent
import navigation.impl.feature.MainRouterComponent
import navigation.impl.feature.SampleOneRouterComponent
import navigation.impl.navgraph.tabview.main.TabViewGraph
import org.koin.core.annotation.Factory
import org.koin.core.component.KoinComponent

@Factory(binds = [RootComponent::class])
class RootComponentImpl(
    componentContext: DefaultComponentContext
) : RootComponent, KoinComponent, ComponentContext by  componentContext {

    private val navigation = StackNavigation<RootConfig>()
    private val childStack: Value<ChildStack<*, RootDestination>> = childStack(
        source = navigation,
        serializer = RootConfig.serializer(),
        initialConfiguration = RootConfig.MainConfig,
        handleBackButton = true,
        childFactory = ::createComponent
    )

    @Composable
    override fun Content() {
        Children(
            stack = childStack,
            modifier = Modifier.fillMaxSize(),
            animation = stackAnimation(slide())
        ) { child ->
            child.instance.component.ContentWithLifecycle()
        }
    }

    private fun createComponent(
        config: RootConfig,
        context: ComponentContext
    ): RootDestination = when (config) {
        is RootConfig.MainConfig -> RootDestination.MainDestination(
            createMainComponent(context, true)
        )

        is RootConfig.SampleOneConfig -> RootDestination.SampleOneDestination(
            createSampleOneRouter(context, config.sampleId)
        )

        is RootConfig.Main2Config -> RootDestination.Main2Destination(
            createMainComponent(context, false)
        )

        is RootConfig.TabsConfig -> RootDestination.TabsDestination(createTabsGraph(context))
    }

    private fun createMainComponent(
        componentContext: ComponentContext,
        isFirstScreen: Boolean
    ): MainRouterComponent = MainRouterComponent(componentContext, isFirstScreen, navigation)

    private fun createSampleOneRouter(
        componentContext: ComponentContext,
        sampleId: Int
    ): SampleOneRouterComponent = SampleOneRouterComponent(componentContext, sampleId, navigation)

    private fun createTabsGraph(componentContext: ComponentContext): TabViewGraph =
        TabViewGraph(componentContext)
}