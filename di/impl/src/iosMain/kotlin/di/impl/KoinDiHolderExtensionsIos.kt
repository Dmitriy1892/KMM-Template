package di.impl

import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import feature.app.InitialDepsHolder
import org.koin.core.Koin
import org.koin.dsl.module

fun KoinDiHolder.Companion.getInstance(): KoinDiHolder = this.getInstance(PlatformConfiguration())

fun KoinDiHolder.Companion.getKoin(): Koin = this.getInstance().koin

fun KoinDiHolder.getInitialDepsHolder(lifecycleRegistry: LifecycleRegistry): InitialDepsHolder {
    this.koin.loadModules(
        modules = listOf(module { single<LifecycleRegistry> { lifecycleRegistry } }),
        allowOverride = true,
        createEagerInstances = true
    )

    return koin.get()
}