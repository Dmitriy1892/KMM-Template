package common.decompose.navigation

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.dmitriy1892.kmm.mvvm.core.store.ViewModelStore

class ViewModelInstanceKeeper(
    private val viewModelStore: ViewModelStore,
    private val viewModelKey: String
) : InstanceKeeper.Instance {

    override fun onDestroy() {
        viewModelStore.remove(viewModelKey)
    }
}