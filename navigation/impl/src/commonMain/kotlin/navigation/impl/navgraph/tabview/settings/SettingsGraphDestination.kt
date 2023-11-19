package navigation.impl.navgraph.tabview.settings

import common.decompose.navigation.Destination
import navigation.impl.feature.tabview.SettingsRouterComponent

sealed interface SettingsGraphDestination {

    val component: Destination

    class SettingsDestination(
        override val component: SettingsRouterComponent
    ) : SettingsGraphDestination
}