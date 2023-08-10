package io.github.dmitriy1892.di.impl.modules

import io.github.dmitriy1892.kmm.mvvm.core.factory.BaseViewModelFactory
import io.github.dmitriy1892.kmm.mvvm.koin.factory.KoinViewModelFactory
import org.koin.dsl.module

val viewModelsModule = module {

    // TODO ADD VIEWMODELS HERE
    single<BaseViewModelFactory> { KoinViewModelFactory() }
}