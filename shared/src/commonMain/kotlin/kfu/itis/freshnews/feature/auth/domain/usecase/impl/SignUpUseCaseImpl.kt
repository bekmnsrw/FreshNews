package kfu.itis.freshnews.feature.auth.domain.usecase.impl

import kfu.itis.freshnews.feature.auth.domain.AuthRepository
import kfu.itis.freshnews.feature.auth.domain.model.UserProfile
import kfu.itis.freshnews.feature.auth.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.Flow

internal class SignUpUseCaseImpl(
    private val authRepository: AuthRepository,
) : SignUpUseCase {

    override suspend fun invoke(login: String, password: String): Flow<UserProfile?> {
        return authRepository.signUp(login, password)
    }
}
