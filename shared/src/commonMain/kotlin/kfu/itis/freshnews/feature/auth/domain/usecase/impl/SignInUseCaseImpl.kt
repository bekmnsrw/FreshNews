package kfu.itis.freshnews.feature.auth.domain.usecase.impl

import kfu.itis.freshnews.feature.auth.domain.AuthRepository
import kfu.itis.freshnews.feature.auth.domain.model.UserProfile
import kfu.itis.freshnews.feature.auth.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.Flow

internal class SignInUseCaseImpl(
    private val authRepository: AuthRepository
) : SignInUseCase {

    override fun invoke(login: String, password: String): Flow<UserProfile?> {
        return authRepository.signIn(login, password)
    }
}
