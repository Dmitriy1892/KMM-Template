package navigation.impl.navgraph.tabview.settings

import kotlinx.serialization.Serializable

@Serializable
sealed interface SettingsGraphConfig {

    @Serializable
    data class Settings(val index: Int) : SettingsGraphConfig
}