package di.impl.modules.feature

import io.github.dmitriy1892.kmm.mvvm.core.factory.BaseViewModelFactory
import io.github.dmitriy1892.kmm.mvvm.core.store.ViewModelStore
import io.github.dmitriy1892.kmm.mvvm.koin.factory.KoinViewModelFactory
import org.koin.dsl.module

val viewModelsModule = module {
    single<BaseViewModelFactory> { KoinViewModelFactory() }
    single<ViewModelStore> { ViewModelStore() }
}