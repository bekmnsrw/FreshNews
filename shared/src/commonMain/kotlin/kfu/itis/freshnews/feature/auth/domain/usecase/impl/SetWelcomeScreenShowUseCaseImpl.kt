package kfu.itis.freshnews.feature.auth.domain.usecase.impl

import kfu.itis.freshnews.feature.auth.domain.AuthRepository
import kfu.itis.freshnews.feature.auth.domain.usecase.SetWelcomeScreenShownUseCase

internal class SetWelcomeScreenShowUseCaseImpl(
    private val authRepository: AuthRepository,
) : SetWelcomeScreenShownUseCase {

    override suspend fun invoke() {
        return authRepository.setWelcomeScreenShown()
    }
}
