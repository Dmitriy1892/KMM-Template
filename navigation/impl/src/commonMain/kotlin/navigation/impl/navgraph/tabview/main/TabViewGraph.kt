package navigation.impl.navgraph.tabview.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.bringToFront
import common.decompose.navigation.NavGraph
import io.github.dmitriy1892.kmm.mvvm.core.viewModelKey
import kotlinx.serialization.KSerializer
import navigation.api.feature.tabview.TabViewRouter
import navigation.impl.navgraph.tabview.add.AddGraph
import navigation.impl.navgraph.tabview.profile.ProfileGraph
import navigation.impl.navgraph.tabview.settings.SettingsGraph
import sample.feature.tabview.main.TabViewViewModel
import sample.feature.tabview.main.model.MenuItem
import sample.feature.tabview.main.view.TabViewScreen

class TabViewGraph(
    componentContext: ComponentContext
) : NavGraph<TabViewConfig, TabDestination>(
    componentContext = componentContext,
    viewModelKey = TabViewViewModel::class.viewModelKey()
), TabViewRouter {

    override val handleBackButton: Boolean = false

    private val destinationInstances = mutableMapOf<String, TabDestination>()

    override fun getInitialConfig(): TabViewConfig = TabViewConfig.ProfileTab

    override fun getConfigSerializer(): KSerializer<TabViewConfig> = TabViewConfig.serializer()


    override fun openAddTab() {
        navigation.bringToFront(TabViewConfig.AddTab)
    }

    override fun openProfileTab() {
        navigation.bringToFront(TabViewConfig.ProfileTab)
    }

    override fun openSettingsTab() {
        navigation.bringToFront(TabViewConfig.SettingsTab)
    }

    override fun createDestination(
        config: TabViewConfig,
        componentContext: ComponentContext
    ): TabDestination {
        return destinationInstances.getOrPut(
            key = config.name,
            defaultValue = {
                when (config) {
                    TabViewConfig.AddTab ->
                        TabDestination.AddTabGraph(AddGraph(componentContext))

                    TabViewConfig.ProfileTab ->
                        TabDestination.ProfileTabGraph(ProfileGraph(componentContext))

                    TabViewConfig.SettingsTab ->
                        TabDestination.SettingsTabGraph(SettingsGraph(componentContext))
                }
            }
        )
    }

    @Composable
    override fun ContentScreen() {
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
                destination.instance.component.Content()
            }
        }
    }
}