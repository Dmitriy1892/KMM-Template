package di.impl.modules

import di.impl.DiImplModule
import di.impl.modules.common.commonModule
import di.impl.modules.core.coreModule
import di.impl.modules.feature.featureModule
import di.impl.modules.navigation.navigationModule
import org.koin.core.module.Module
import org.koin.ksp.generated.module

val mainModule = listOf<Module>(
    initialDepsModule,
    commonModule,
    coreModule,
    navigationModule,
    featureModule,
    DiImplModule().module,
)