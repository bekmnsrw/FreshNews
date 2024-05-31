package kfu.itis.freshnews.feature.auth.domain

import kfu.itis.freshnews.feature.auth.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun signUp(login: String, password: String): Flow<UserProfile?>
    fun signIn(login: String, password: String): Flow<UserProfile?>
    suspend fun saveUserId(id: Int)
    suspend fun getUserId(): Int?
    suspend fun setWelcomeScreenShown()
    suspend fun isWelcomeScreenShown(): Boolean
}
