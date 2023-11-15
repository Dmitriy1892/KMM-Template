package sample.feature.tabview.main.model

import androidx.compose.runtime.Immutable

@Immutable
data class TabViewState(
    val isProgress: Boolean = false
)

enum class MenuItem {
    ADD,
    PROFILE,
    SETTINGS
}