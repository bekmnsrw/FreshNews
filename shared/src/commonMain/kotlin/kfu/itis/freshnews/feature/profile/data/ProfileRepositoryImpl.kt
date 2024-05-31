package kfu.itis.freshnews.feature.profile.data

import com.russhwolf.settings.Settings
import kfu.itis.freshnews.feature.profile.data.datasource.local.LocalProfileDataSource
import kfu.itis.freshnews.feature.profile.data.mapper.toUserProfile
import kfu.itis.freshnews.feature.profile.domain.ProfileRepository
import kfu.itis.freshnews.feature.profile.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ProfileRepositoryImpl(
    private val localProfileDataSource: LocalProfileDataSource,
    private val settings: Settings,
) : ProfileRepository {

    override fun getUserProfile(id: Long): Flow<UserProfile?> {
        return localProfileDataSource.getProfile(id)
            .map { profile -> profile?.toUserProfile() }
    }

    override suspend fun deleteUserProfile(id: Long) {
        localProfileDataSource.deleteProfile(id)
    }

    override suspend fun isDarkModeEnabled(): Boolean {
        return settings.getBoolean(IS_DARK_MODE_ENABLED, false)
    }

    override suspend fun changeDarkMode(isDarkModeEnabled: Boolean) {
        settings.putBoolean(IS_DARK_MODE_ENABLED, isDarkModeEnabled)
    }

    private companion object {

        const val IS_DARK_MODE_ENABLED = "isDarkModeEnabled"
    }
}
