package sample.feature.sample.one.ui

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.adapters.ImmutableMapAdapter
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

@Immutable
data class SampleOneState(
    val id: Int,
    val isProgress: Boolean = false,
    val list: ImmutableList<UiItem> = persistentListOf(),
)

@Immutable
data class UiItem(
    val id: String,
    val parentId: String,
    val name: String,
    val type: UiItemType,
    val isVisible: Boolean,
)

@Immutable
enum class UiItemType {
    PARENT,
    CHILD,
    SUB_CHILD
}