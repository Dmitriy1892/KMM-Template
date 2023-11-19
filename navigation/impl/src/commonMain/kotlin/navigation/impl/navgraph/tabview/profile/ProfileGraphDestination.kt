package navigation.impl.navgraph.tabview.profile

import common.decompose.navigation.Destination
import navigation.impl.feature.tabview.ProfileRouterComponent

sealed interface ProfileGraphDestination {

    val component: Destination

    data class ProfileDestination(
        override val component: ProfileRouterComponent
    ) : ProfileGraphDestination
}