package di.impl.modules.feature

import sample.feature.main.FeatureMainUiModule
import sample.feature.sample.one.FeatureSampleOneModule
import sample.feature.tabview.FeatureTabViewModule
import org.koin.dsl.module
import org.koin.ksp.generated.module

val featureModule = module {
    includes(
        viewModelsModule,
        FeatureMainUiModule().module,
        FeatureSampleOneModule().module,
        FeatureTabViewModule().module
    )
}