package navigation.impl.navgraph.tabview.profile

import navigation.impl.BaseDestinationComponent
import navigation.impl.feature.tabview.ProfileRouterComponent

sealed interface ProfileGraphDestination {

    val component: BaseDestinationComponent

    data class ProfileDestination(
        override val component: ProfileRouterComponent
    ) : ProfileGraphDestination
}