package kfu.itis.freshnews.feature.auth.domain

import kfu.itis.freshnews.feature.auth.domain.model.AuthModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun signUp(login: String, password: String): Flow<AuthModel?>
    fun signIn(login: String, password: String): Flow<AuthModel?>
    suspend fun saveUserId(id: Long)
    suspend fun getUserId(): Long?
    suspend fun setWelcomeScreenShown()
    suspend fun isWelcomeScreenShown(): Boolean
    suspend fun logOut()
}
