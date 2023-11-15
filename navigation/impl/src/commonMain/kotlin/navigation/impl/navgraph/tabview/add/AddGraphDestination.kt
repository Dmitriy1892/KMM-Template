package navigation.impl.navgraph.tabview.add

import navigation.impl.BaseDestinationComponent
import navigation.impl.feature.tabview.AddRouterComponent

sealed interface AddGraphDestination {

    val component: BaseDestinationComponent

    class Add(override val component: AddRouterComponent) : AddGraphDestination
}