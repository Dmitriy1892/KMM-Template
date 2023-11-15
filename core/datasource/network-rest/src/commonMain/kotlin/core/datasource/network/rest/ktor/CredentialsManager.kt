package core.datasource.network.rest.ktor

interface CredentialsManager {

    suspend fun getAccessToken(): String

    suspend fun getRefreshToken(): String

    suspend fun updateAccessToken(accessToken: String)

    suspend fun updateRefreshToken(refreshToken: String)
}