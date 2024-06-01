package kfu.itis.freshnews.feature.profile.domain.usecase

import kfu.itis.freshnews.feature.profile.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface GetProfileUseCase {

    operator fun invoke(id: Long): Flow<UserProfile?>
}
