package kfu.itis.freshnews.feature.auth.data

import com.russhwolf.settings.Settings
import kfu.itis.freshnews.feature.auth.data.datasource.local.LocalAuthDataSource
import kfu.itis.freshnews.feature.auth.data.mapper.toUserProfile
import kfu.itis.freshnews.feature.auth.domain.AuthRepository
import kfu.itis.freshnews.feature.auth.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

internal class AuthRepositoryImpl(
    private val localAuthDataSource: LocalAuthDataSource,
    private val settings: Settings,
) : AuthRepository {

    override suspend fun signUp(login: String, password: String): Flow<UserProfile?> {
        val isExists = localAuthDataSource.isProfileExists(login).first()
        if (isExists) {
            return flowOf(null)
        } else {
            localAuthDataSource.signUp(login, password)
            return localAuthDataSource.signIn(login, password)
                .map { profile -> profile?.toUserProfile() }
        }
    }

    override fun signIn(login: String, password: String): Flow<UserProfile?> {
        return localAuthDataSource.signIn(login, password)
            .map { profile -> profile?.toUserProfile() }
    }

    override suspend fun saveUserId(id: Int) {
        settings.putInt(USER_ID_KEY, id)
    }

    override suspend fun getUserId(): Int? {
        return settings.getIntOrNull(USER_ID_KEY)
    }

    private companion object {

        const val USER_ID_KEY = "id"
    }
}
