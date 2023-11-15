package di.impl

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import feature.app.InitialDepsHolder
import feature.app.RootComponent
import io.github.dmitriy1892.kmm.mvvm.core.factory.BaseViewModelFactory
import io.github.dmitriy1892.kmm.mvvm.core.store.ViewModelStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

class InitialDepsHolderIos(
    lifecycleRegistry: LifecycleRegistry
) : InitialDepsHolder, KoinComponent {

    override val viewModelFactory: BaseViewModelFactory by inject()

    override val viewModelStore: ViewModelStore by inject()

    override val backDispatcher: BackDispatcher by inject()

    override val rootComponent: RootComponent by inject()

    init {
        getKoin().loadModules(
            modules = listOf(
                module {
                    single<BackDispatcher> { BackDispatcher() }
                    single<DefaultComponentContext> {
                        DefaultComponentContext(
                            lifecycle = lifecycleRegistry,
                            backHandler = get<BackDispatcher>()
                        )
                    }
                }
            ),
            allowOverride = true
        )
    }
}