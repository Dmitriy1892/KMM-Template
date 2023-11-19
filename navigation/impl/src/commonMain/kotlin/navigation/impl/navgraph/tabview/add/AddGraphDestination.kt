package navigation.impl.navgraph.tabview.add

import common.decompose.navigation.Destination
import navigation.impl.feature.tabview.AddRouterComponent

sealed interface AddGraphDestination {

    val component: Destination

    class Add(override val component: AddRouterComponent) : AddGraphDestination
}