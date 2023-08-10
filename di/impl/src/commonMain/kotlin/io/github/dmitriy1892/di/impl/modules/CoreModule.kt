package io.github.dmitriy1892.di.impl.modules

import org.koin.core.module.Module

val coreModule = listOf<Module>(
    utilsModule,
    localDataSourceModule,
    networkDataSourceModule,
    dataModule,
    domainModule,
    viewModelsModule,
    navigationModule
)