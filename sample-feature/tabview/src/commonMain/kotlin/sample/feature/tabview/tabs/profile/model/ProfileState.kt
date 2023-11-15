package sample.feature.tabview.tabs.profile.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProfileState(
    val isProgress: Boolean = false,
    val screenIndex: Int
)