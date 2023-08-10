package io.github.dmitriy1892.di.impl

import io.github.dmitriy1892.core.datasource.network.ktor.CredentialsManager

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