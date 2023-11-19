package navigation.impl.navgraph.root

import common.decompose.navigation.Destination
import navigation.impl.feature.MainRouterComponent
import navigation.impl.feature.SampleOneRouterComponent
import navigation.impl.navgraph.tabview.main.TabViewGraph

sealed interface RootDestination {

    val component: Destination

    class MainDestination(override val component: MainRouterComponent) : RootDestination

    class SampleOneDestination(override val component: SampleOneRouterComponent) : RootDestination

    class Main2Destination(override val component: MainRouterComponent) : RootDestination

    class TabsDestination(override val component: TabViewGraph) : RootDestination
}