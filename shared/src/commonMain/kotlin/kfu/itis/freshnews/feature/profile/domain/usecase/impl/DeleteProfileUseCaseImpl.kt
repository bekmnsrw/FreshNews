package kfu.itis.freshnews.feature.profile.domain.usecase.impl

import kfu.itis.freshnews.feature.profile.domain.ProfileRepository
import kfu.itis.freshnews.feature.profile.domain.usecase.DeleteProfileUseCase

internal class DeleteProfileUseCaseImpl(
    private val profileRepository: ProfileRepository,
) : DeleteProfileUseCase {

    override suspend fun invoke(id: Long) {
        return profileRepository.deleteUserProfile(id)
    }
}
