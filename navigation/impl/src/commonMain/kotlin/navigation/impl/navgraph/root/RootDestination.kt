package navigation.impl.navgraph.root

import navigation.impl.BaseComponent
import navigation.impl.BaseDestinationComponent
import navigation.impl.feature.MainRouterComponent
import navigation.impl.feature.SampleOneRouterComponent
import navigation.impl.navgraph.tabview.main.TabViewGraph

sealed interface RootDestination {

    val component: BaseComponent

    class MainDestination(override val component: MainRouterComponent) : RootDestination

    class SampleOneDestination(override val component: SampleOneRouterComponent) : RootDestination

    class Main2Destination(override val component: MainRouterComponent) : RootDestination

    class TabsDestination(override val component: TabViewGraph) : RootDestination
}