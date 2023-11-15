package di.impl

import io.github.dmitriy1892.kmm.mvvm.core.factory.BaseViewModelFactory
import feature.app.InitialDepsHolder
import feature.app.RootComponent
import io.github.dmitriy1892.kmm.mvvm.core.store.ViewModelStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InitialDepsHolderAndroid : InitialDepsHolder, KoinComponent {

    override val viewModelStore: ViewModelStore by inject()

    override val viewModelFactory: BaseViewModelFactory by inject()

    override val rootComponent: RootComponent by inject()
}