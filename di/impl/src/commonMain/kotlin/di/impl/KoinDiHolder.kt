package di.impl

import di.impl.modules.mainModule
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import common.platform.logger.KmmLogger
import io.github.dmitriy1892.kmm.utils.coroutines.mutex.withThreadLock
import kotlinx.coroutines.sync.Mutex
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
                        KmmLogger.initialize()
                        modules(
                            module { single { platformConfiguration } },
                            *mainModule.toTypedArray()
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