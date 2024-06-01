package kfu.itis.freshnews.feature.profile.domain.usecase.impl

import kfu.itis.freshnews.feature.profile.domain.ProfileRepository
import kfu.itis.freshnews.feature.profile.domain.model.UserProfile
import kfu.itis.freshnews.feature.profile.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.flow.Flow

internal class GetProfileUseCaseImpl(
    private val profileRepository: ProfileRepository,
) : GetProfileUseCase {

    override fun invoke(id: Long): Flow<UserProfile?> {
        return profileRepository.getUserProfile(id)
    }
}
