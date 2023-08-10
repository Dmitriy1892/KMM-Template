package io.github.dmitriy1892.di.impl

import io.github.dmitriy1892.kmm.utils.coroutines.withThreadLock
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import kotlinx.coroutines.sync.Mutex
import io.github.dmitriy1892.di.impl.modules.coreModule
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import kotlin.native.concurrent.ThreadLocal

class KoinDiHolder private constructor(di: KoinApplication) {

    val koin: Koin by lazy { di.koin }

    @ThreadLocal
    companion object {
        private var INSTANCE: KoinDiHolder? = null
        private val mutex = Mutex()

        fun getInstance(platformConfiguration: PlatformConfiguration): KoinDiHolder {
            return mutex.withThreadLock {
                INSTANCE ?: run {
                    val koinApp = startKoin {
                        modules(
                            module { single { platformConfiguration } },
                            *coreModule.toTypedArray()
                        )
                    }

                    INSTANCE = KoinDiHolder(koinApp)
                    INSTANCE!!
                }
            }
        }

        fun getKoin(platformConfiguration: PlatformConfiguration): Koin =
            getInstance(platformConfiguration).koin
    }
}