package navigation.impl.navgraph.tabview.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.github.dmitriy1892.kmm.mvvm.core.viewModelKey
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import navigation.api.feature.tabview.TabViewRouter
import sample.feature.tabview.main.view.TabViewScreen
import sample.feature.tabview.main.TabViewViewModel
import sample.feature.tabview.main.model.MenuItem
import navigation.impl.BaseDestinationComponent
import navigation.impl.navgraph.tabview.add.AddGraph
import navigation.impl.navgraph.tabview.profile.ProfileGraph
import navigation.impl.navgraph.tabview.settings.SettingsGraph

class TabViewGraph(
    componentContext: ComponentContext
) : BaseDestinationComponent(componentContext), TabViewRouter {

    override val viewModelKey: String = TabViewViewModel::class.viewModelKey()

    private val destinationInstances = mutableMapOf<String, TabDestination>()

    private val navigation = StackNavigation<TabViewConfig>()
    private val childStack: Value<ChildStack<*, TabDestination>> = childStack(
        source = navigation,
        serializer = TabViewConfig.serializer(),
        initialConfiguration = TabViewConfig.ProfileTab,
        handleBackButton = false,
        childFactory = ::getOrCreateDestination
    )

    override fun openAddTab() {
        navigation.bringToFront(TabViewConfig.AddTab)
    }

    override fun openProfileTab() {
        navigation.bringToFront(TabViewConfig.ProfileTab)
    }

    override fun openSettingsTab() {
        navigation.bringToFront(TabViewConfig.SettingsTab)
    }

    @Composable
    override fun Content() {
        val currentStack by childStack.subscribeAsState()
        TabViewScreen(
            selectedMenuItem = remember {{
                (currentStack.active.configuration as TabViewConfig).menuItem
            }},
            openTabContent = remember {{ menuItem ->
                when (menuItem) {
                    MenuItem.ADD -> openAddTab()
                    MenuItem.PROFILE -> openProfileTab()
                    MenuItem.SETTINGS -> openSettingsTab()
                }
            }}
        ) {
            Children(
                modifier = Modifier.fillMaxSize(),
                stack = currentStack,
                animation = stackAnimation(fade())
            ) { destination ->
                destination.instance.component.ContentWithLifecycle()
            }
        }
    }

    private fun getOrCreateDestination(
        tabViewConfig: TabViewConfig,
        componentContext: ComponentContext
    ): TabDestination = destinationInstances.getOrPut(
        key = tabViewConfig.name,
        defaultValue = { createDestination(tabViewConfig, componentContext) }
    )

    private fun createDestination(
        tabViewConfig: TabViewConfig,
        componentContext: ComponentContext
    ): TabDestination = when (tabViewConfig) {
        TabViewConfig.AddTab -> TabDestination.AddTabGraph(AddGraph(componentContext))
        TabViewConfig.ProfileTab -> TabDestination.ProfileTabGraph(ProfileGraph(componentContext))
        TabViewConfig.SettingsTab -> TabDestination.SettingsTabGraph(SettingsGraph(componentContext))
    }
}