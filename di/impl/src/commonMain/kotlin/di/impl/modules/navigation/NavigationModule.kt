package di.impl.modules.navigation

import navigation.impl.NavigationImplModule
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module

val navigationModule: Module = module {
    includes(NavigationImplModule().module)
}