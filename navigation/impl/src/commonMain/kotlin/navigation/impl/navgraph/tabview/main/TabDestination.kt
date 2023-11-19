package navigation.impl.navgraph.tabview.main

import common.decompose.navigation.Destination
import navigation.impl.navgraph.tabview.add.AddGraph
import navigation.impl.navgraph.tabview.profile.ProfileGraph
import navigation.impl.navgraph.tabview.settings.SettingsGraph

interface TabDestination {

    val component: Destination

    data class AddTabGraph(override val component: AddGraph) : TabDestination

    data class ProfileTabGraph(override val component: ProfileGraph) : TabDestination

    data class SettingsTabGraph(override val component: SettingsGraph) : TabDestination
}