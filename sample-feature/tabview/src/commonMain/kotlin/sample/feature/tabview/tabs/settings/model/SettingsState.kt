package sample.feature.tabview.tabs.settings.model

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsState(
    val isProgress: Boolean = false,
    val screenIndex: Int
)