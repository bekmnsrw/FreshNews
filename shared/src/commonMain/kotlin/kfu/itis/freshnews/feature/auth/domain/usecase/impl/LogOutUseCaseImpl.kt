package kfu.itis.freshnews.feature.auth.domain.usecase.impl

import kfu.itis.freshnews.feature.auth.domain.AuthRepository
import kfu.itis.freshnews.feature.auth.domain.usecase.LogOutUseCase

internal class LogOutUseCaseImpl(
    private val authRepository: AuthRepository,
) : LogOutUseCase {

    override suspend operator fun invoke() {
        return authRepository.logOut()
    }
}
