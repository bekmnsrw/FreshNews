package kfu.itis.freshnews.feature.auth.data

import com.russhwolf.settings.Settings
import kfu.itis.freshnews.feature.auth.data.datasource.local.LocalAuthDataSource
import kfu.itis.freshnews.feature.auth.data.mapper.toAuthModel
import kfu.itis.freshnews.feature.auth.domain.AuthRepository
import kfu.itis.freshnews.feature.auth.domain.model.AuthModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

internal class AuthRepositoryImpl(
    private val localAuthDataSource: LocalAuthDataSource,
    private val settings: Settings,
) : AuthRepository {

    override suspend fun signUp(login: String, password: String): Flow<AuthModel?> {
        val isExists = localAuthDataSource.isProfileExists(login).first()
        if (isExists) {
            return flowOf(null)
        } else {
            localAuthDataSource.signUp(login, password)
            return localAuthDataSource.signIn(login, password)
                .map { profile -> profile?.toAuthModel() }
        }
    }

    override fun signIn(login: String, password: String): Flow<AuthModel?> {
        return localAuthDataSource.signIn(login, password)
            .map { profile -> profile?.toAuthModel() }
    }

    override suspend fun saveUserId(id: Long) {
        settings.putLong(USER_ID_KEY, id)
    }

    override suspend fun getUserId(): Long? {
        return settings.getLongOrNull(USER_ID_KEY)
    }

    override suspend fun setWelcomeScreenShown() {
        settings.putBoolean(IS_WELCOME_SCREEN_SHOWN_KEY, true)
    }

    override suspend fun isWelcomeScreenShown(): Boolean {
        return settings.getBoolean(IS_WELCOME_SCREEN_SHOWN_KEY, false)
    }

    override suspend fun logOut() {
        settings.remove(USER_ID_KEY)
    }

    private companion object {

        const val USER_ID_KEY = "id"
        const val IS_WELCOME_SCREEN_SHOWN_KEY = "isWelcomeScreenShown"
    }
}
