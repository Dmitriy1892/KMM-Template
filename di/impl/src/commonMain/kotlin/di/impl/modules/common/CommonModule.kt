package di.impl.modules.common

import di.api.APP_COROUTINE_SCOPE
import io.github.dmitriy1892.kmm.utils.coroutines.dispatcher.CoroutineDispatcherProvider
import io.github.dmitriy1892.kmm.utils.coroutines.dispatcher.CoroutineDispatcherProviderImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
    single<CoroutineDispatcherProvider> { CoroutineDispatcherProviderImpl() }

    single<CoroutineScope>(named(APP_COROUTINE_SCOPE)) {
        val dispatcher = get<CoroutineDispatcherProvider>().main
        CoroutineScope(dispatcher + SupervisorJob())
    }
}