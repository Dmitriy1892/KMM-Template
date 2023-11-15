package di.impl

import core.datasource.network.rest.ktor.CredentialsManager
import org.koin.core.annotation.Single

@Single(binds = [CredentialsManager::class])
class CredentialsManagerImpl : CredentialsManager {

    override suspend fun getAccessToken(): String {
        TODO("Not yet implemented")
    }

    override suspend fun getRefreshToken(): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateAccessToken(accessToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRefreshToken(refreshToken: String) {
        TODO("Not yet implemented")
    }
}