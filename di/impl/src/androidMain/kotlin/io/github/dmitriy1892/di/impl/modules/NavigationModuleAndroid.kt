package io.github.dmitriy1892.di.impl.modules

import io.github.dmitriy1892.navigation.api.GlobalNavigator
import io.github.dmitriy1892.navigation.impl.GlobalNavigatorImpl
import org.koin.core.module.Module
import org.koin.dsl.module

actual val navigationModule: Module = module {
    single<GlobalNavigator> { GlobalNavigatorImpl() }
}