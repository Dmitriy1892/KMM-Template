package navigation.impl.navgraph.tabview.settings

import navigation.impl.BaseDestinationComponent

sealed interface SettingsGraphDestination {

    val component: BaseDestinationComponent

    class SettingsDestination(
        override val component: BaseDestinationComponent
    ) : SettingsGraphDestination
}