package kfu.itis.freshnews.feature.auth.domain.usecase.impl

import kfu.itis.freshnews.feature.auth.domain.AuthRepository
import kfu.itis.freshnews.feature.auth.domain.usecase.IsWelcomeScreenShownUseCase

internal class IsWelcomeScreenShownUseCaseImpl(
    private val authRepository: AuthRepository,
) : IsWelcomeScreenShownUseCase {

    override suspend fun invoke(): Boolean {
        return authRepository.isWelcomeScreenShown()
    }
}
