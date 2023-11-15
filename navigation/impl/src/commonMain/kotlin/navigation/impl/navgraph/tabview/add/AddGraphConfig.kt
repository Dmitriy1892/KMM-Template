package navigation.impl.navgraph.tabview.add

import kotlinx.serialization.Serializable

@Serializable
sealed interface AddGraphConfig {

    @Serializable
    data class AddScreen(val index: Int) : AddGraphConfig
}