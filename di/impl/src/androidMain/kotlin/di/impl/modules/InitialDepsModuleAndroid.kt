package di.impl.modules

import di.impl.InitialDepsHolderAndroid
import feature.app.InitialDepsHolder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val initialDepsModule: Module = module {
    factory<InitialDepsHolder> { InitialDepsHolderAndroid() }
}