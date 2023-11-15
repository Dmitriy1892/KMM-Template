package sample.feature.main.ui

import androidx.compose.runtime.Immutable

@Immutable
data class MainState(
    val someText: String,
    val isProgress: Boolean = false
)