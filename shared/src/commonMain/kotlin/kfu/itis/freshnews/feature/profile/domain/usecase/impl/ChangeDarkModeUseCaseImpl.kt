package kfu.itis.freshnews.feature.profile.domain.usecase.impl

import kfu.itis.freshnews.feature.profile.domain.ProfileRepository
import kfu.itis.freshnews.feature.profile.domain.usecase.ChangeDarkModeUseCase

internal class ChangeDarkModeUseCaseImpl(
    private val profileRepository: ProfileRepository,
) : ChangeDarkModeUseCase {

    override suspend fun invoke(isDarkModeEnabled: Boolean) {
        return profileRepository.changeDarkMode(isDarkModeEnabled)
    }
}
