package sample.feature.sample.one.ui

import androidx.compose.runtime.Immutable
import io.github.dmitriy1892.kmm.mvi.kmm.mvvm.MviViewModel
import io.github.dmitriy1892.kmm.mvvm.core.BaseViewModel
import io.github.dmitriy1892.kmm.mvi.core.extensions.intent
import io.github.dmitriy1892.kmm.mvvm.core.VIEW_MODEL_KEY_PREFIX
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Named

@Factory(binds = [BaseViewModel::class])
@Named(value = "$VIEW_MODEL_KEY_PREFIX:sample.feature.sample.one.ui.SampleOneViewModel")
class SampleOneViewModel(
    @InjectedParam val sampleId: Int
) : MviViewModel<SampleOneState, SampleOneSideEffect>() {

    override val initialState: SampleOneState = SampleOneState(id = sampleId)

    private var data: PersistentMap<String, ParentItem> = persistentMapOf()

    init {
        intent {
            reduceState { state -> state.copy(isProgress = true) }

            withContext(Dispatchers.IO) {
                data = generateModels()
            }

            val list = withContext(Dispatchers.IO) {
                data.values
                    .map { parent ->
                        UiItem(
                            id = parent.name,
                            parentId = parent.name,
                            name = parent.name,
                            type = UiItemType.PARENT,
                            isVisible = true
                        )
                    }
                    .toImmutableList()
            }

            reduceState { state -> state.copy(list = list, isProgress = false) }
        }
    }

    fun onItemClick(index: Int): Unit { intent {
        val currentList = state.list
        val pickedItem = currentList[index]

        val resultList = withContext(Dispatchers.Default) {
            when (pickedItem.type) {
                UiItemType.PARENT -> reduceListBasedOnParent(currentList, index)
                UiItemType.CHILD -> reduceListBasedOnChild(currentList, index)
                UiItemType.SUB_CHILD -> reduceListBasedOnSubChild(currentList, index)
            }
        }

        reduceState { state -> state.copy(list = resultList) }
    }
    }

    fun reduceListBasedOnParent(
        currentList: List<UiItem>,
        currentItemIndex: Int
    ): ImmutableList<UiItem> {
        val pickedItem = currentList[currentItemIndex]
        require(pickedItem.type == UiItemType.PARENT)

        val nextIndex = currentItemIndex + 1
        val nextItem = currentList.getOrNull(nextIndex)

        val outList = if (nextItem?.parentId == pickedItem.id) {
            val mutableList = currentList.toMutableList()

            while (mutableList.getOrNull(nextIndex)?.parentId == pickedItem.id) {
                val child = mutableList[nextIndex]

                while (mutableList.getOrNull(nextIndex + 1)?.parentId == child.id) {
                    mutableList.removeAt(nextIndex + 1)
                }

                mutableList.removeAt(nextIndex)
            }
            mutableList.toImmutableList()
        } else {
            val childs = data[pickedItem.id]?.childItems?.values
                ?.map { child ->
                    UiItem(
                        id = child.name,
                        parentId = pickedItem.id,
                        name = child.name,
                        type = UiItemType.CHILD,
                        isVisible = true
                    )
                }
                ?: emptyList()

            val mutableList = currentList.toMutableList()
            mutableList.addAll(nextIndex, childs)
            mutableList.toImmutableList()
        }

        return outList
    }

    fun reduceListBasedOnChild(
        currentList: List<UiItem>,
        currentItemIndex: Int
    ): ImmutableList<UiItem> {
        val pickedItem = currentList[currentItemIndex]
        require(pickedItem.type == UiItemType.CHILD)

        val nextIndex = currentItemIndex + 1
        val nextItem = currentList.getOrNull(nextIndex)

        val outList = if (nextItem?.parentId == pickedItem.id) {
            val mutableList = currentList.toMutableList()
            while (mutableList.getOrNull(nextIndex)?.parentId == pickedItem.id) {
                mutableList.removeAt(nextIndex)
            }
            mutableList.toImmutableList()
        } else {
            val childs = data[pickedItem.parentId]
                ?.childItems
                ?.get(pickedItem.id)
                ?.subChildItems
                ?.values
                ?.map { sub ->
                    UiItem(
                        id = sub.name,
                        parentId = pickedItem.id,
                        name = sub.name,
                        type = UiItemType.SUB_CHILD,
                        isVisible = false
                    )
                }
                ?: emptyList()

            val mutableList = currentList.toMutableList()
            mutableList.addAll(nextIndex, childs)
            mutableList.toImmutableList()
        }

        return outList
    }

    fun reduceListBasedOnSubChild(
        currentList: List<UiItem>,
        currentItemIndex: Int
    ): ImmutableList<UiItem> {
        val pickedItem = currentList[currentItemIndex]
        require(pickedItem.type == UiItemType.SUB_CHILD)

        val newState = pickedItem.copy(isVisible = pickedItem.isVisible.not())

        return currentList
            .toMutableList()
            .apply { set(currentItemIndex, newState) }
            .toImmutableList()
    }

    private fun generateModels(): PersistentMap<String, ParentItem> {
        return generateList(
            size = 2000,
            mapKey = { parent -> parent.name }
        ) { index ->
            ParentItem(
                name = "Parent #$index",
                childItems = generateList(
                    size = 10,
                    mapKey = { child -> child.name }
                ) { childIndex ->
                    ChildItem(
                        name = "Child #$childIndex, parent #$index",
                        subChildItems = generateList(
                            size = 10,
                            mapKey = { sub -> sub.name }
                        ) { subChildIndex ->
                            SubChildItem(
                                name = "SubChild #$subChildIndex #$childIndex parent #$index"
                            )
                        }
                    )
                }
            )
        }
    }

    private fun <T, R> generateList(
        size: Int,
        mapKey: (T) -> R,
        createItem: (Int) -> T
    ): PersistentMap<R, T> {
        val list = mutableListOf<T>()
        repeat(size) { index ->
            val item = createItem(index)
            list.add(item)
        }

        return list.associateBy { mapKey(it) }.toPersistentMap()
    }
}

@Immutable
data class ParentItem(
    val name: String,
    val childItems: ImmutableMap<String, ChildItem>,
    val isExpanded: Boolean = false
)

@Immutable
data class ChildItem(
    val name: String,
    val subChildItems: ImmutableMap<String, SubChildItem>,
    val isExpanded: Boolean = false
)

@Immutable
data class SubChildItem(
    val name: String,
    val isChecked: Boolean = false
)