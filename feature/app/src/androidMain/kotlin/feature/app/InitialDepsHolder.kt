package feature.app

import io.github.dmitriy1892.kmm.mvvm.core.factory.BaseViewModelFactory
import io.github.dmitriy1892.kmm.mvvm.core.store.ViewModelStore

actual interface InitialDepsHolder {

    actual val viewModelStore: ViewModelStore
    actual val viewModelFactory: BaseViewModelFactory
    actual val rootComponent: RootComponent
}