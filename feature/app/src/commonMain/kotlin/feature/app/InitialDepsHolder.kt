package feature.app

import io.github.dmitriy1892.kmm.mvvm.core.factory.BaseViewModelFactory
import io.github.dmitriy1892.kmm.mvvm.core.store.ViewModelStore

expect interface InitialDepsHolder {

    val viewModelStore: ViewModelStore
    val viewModelFactory: BaseViewModelFactory
    val rootComponent: RootComponent
}