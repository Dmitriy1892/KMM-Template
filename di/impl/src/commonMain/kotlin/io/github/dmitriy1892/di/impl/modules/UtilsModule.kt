package io.github.dmitriy1892.di.impl.modules

import io.github.dmitriy1892.kmm.utils.coroutines.CoroutineDispatcherProvider
import io.github.dmitriy1892.kmm.utils.coroutines.CoroutineDispatcherProviderImpl
import org.koin.dsl.module

val utilsModule = module {
    single<CoroutineDispatcherProvider> { CoroutineDispatcherProviderImpl() }
}