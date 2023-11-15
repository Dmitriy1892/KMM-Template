package sample.feature.tabview.tabs.add.model

import androidx.compose.runtime.Immutable

@Immutable
data class AddState(
    val isProgress: Boolean = false,
    val screenIndex: Int
)