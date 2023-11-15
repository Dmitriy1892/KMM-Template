package navigation.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.dmitriy1892.kmm.mvvm.core.store.ViewModelStore
import org.koin.core.component.inject

abstract class BaseDestinationComponent(
    componentContext: ComponentContext
) : BaseComponent(componentContext) {

    private val viewModelStore: ViewModelStore by inject()
    protected abstract val viewModelKey: String

    init {
        keepViewModelInstance()
    }

    private fun keepViewModelInstance() {
        instanceKeeper.getOrCreate {
            ViewModelInstanceKeeper({ viewModelStore }, { viewModelKey })
        }
    }
}

private class ViewModelInstanceKeeper(
    private val viewModelStoreProvider: () -> ViewModelStore,
    private val viewModelKeyProvider: () -> String
) : InstanceKeeper.Instance {

    override fun onDestroy() {
        viewModelStoreProvider().remove(viewModelKeyProvider())
    }
}