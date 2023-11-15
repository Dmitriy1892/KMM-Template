package di.impl.modules.core.datasource

import io.github.dmitriy1892.kmm.utils.platform.Config
import core.datasource.network.rest.NetworkRestDataSourceModule
import core.datasource.network.rest.ktor.base.BASE_URL_QUALIFIER
import core.datasource.network.rest.ktor.client.SampleNetworkClient
import core.datasource.network.rest.service.SampleService
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ksp.generated.module

val PROD_URL = "https://dummyjson.com/"
val DEV_URL = "https://dummyjson.com/"

val networkDataSourceModule = module {
    includes(NetworkRestDataSourceModule().module)

    single(named(BASE_URL_QUALIFIER)) { if (Config.isDebugBuild) DEV_URL else PROD_URL }
    single<Ktorfit> { get<SampleNetworkClient>().ktorfit }

    single<SampleService> { get<Ktorfit>().create<SampleService>() }
}