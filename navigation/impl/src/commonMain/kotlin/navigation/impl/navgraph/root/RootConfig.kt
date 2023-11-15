package navigation.impl.navgraph.root

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootConfig {

    @Serializable
    data object MainConfig : RootConfig

    @Serializable
    data class SampleOneConfig(val sampleId: Int) : RootConfig

    @Serializable
    data object Main2Config : RootConfig

    @Serializable
    data object TabsConfig : RootConfig
}