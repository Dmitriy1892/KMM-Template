package navigation.impl.navgraph.tabview.main

import sample.feature.tabview.main.model.MenuItem
import kotlinx.serialization.Serializable

@Serializable
sealed class TabViewConfig(val name: String, val menuItem: MenuItem) {

    @Serializable
    data object AddTab : TabViewConfig("AddTab", MenuItem.ADD)

    @Serializable
    data object ProfileTab : TabViewConfig("ProfileTab", MenuItem.PROFILE)

    @Serializable
    data object SettingsTab : TabViewConfig("SettingsTab", MenuItem.SETTINGS)
}