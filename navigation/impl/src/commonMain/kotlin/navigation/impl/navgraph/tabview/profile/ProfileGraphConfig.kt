package navigation.impl.navgraph.tabview.profile

import kotlinx.serialization.Serializable

@Serializable
sealed interface ProfileGraphConfig {

    @Serializable
    data class Profile(val index: Int) : ProfileGraphConfig
}