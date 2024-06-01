package kfu.itis.freshnews.feature.profile.domain.usecase.impl

import kfu.itis.freshnews.feature.profile.domain.ProfileRepository
import kfu.itis.freshnews.feature.profile.domain.usecase.IsDarkModeEnabledUseCase

internal class IsDarkModeEnabledUseCaseImpl(
    private val profileRepository: ProfileRepository,
) : IsDarkModeEnabledUseCase {

    override suspend fun invoke(): Boolean {
        return profileRepository.isDarkModeEnabled()
    }
}
