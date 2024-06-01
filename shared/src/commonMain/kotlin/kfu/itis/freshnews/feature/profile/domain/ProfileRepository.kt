package kfu.itis.freshnews.feature.profile.domain

import kfu.itis.freshnews.feature.profile.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getUserProfile(id: Long): Flow<UserProfile?>
    suspend fun deleteUserProfile(id: Long)
    suspend fun isDarkModeEnabled(): Boolean
    suspend fun changeDarkMode(isDarkModeEnabled: Boolean)
}
