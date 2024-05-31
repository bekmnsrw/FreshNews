package kfu.itis.freshnews.feature.auth.domain.usecase

import kfu.itis.freshnews.feature.auth.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface SignInUseCase {

    operator fun invoke(login: String, password: String): Flow<UserProfile?>
}
