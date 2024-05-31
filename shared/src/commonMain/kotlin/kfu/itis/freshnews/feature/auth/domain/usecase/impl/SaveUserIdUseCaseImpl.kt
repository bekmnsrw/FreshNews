package kfu.itis.freshnews.feature.auth.domain.usecase.impl

import kfu.itis.freshnews.feature.auth.domain.AuthRepository
import kfu.itis.freshnews.feature.auth.domain.usecase.SaveUserIdUseCase

internal class SaveUserIdUseCaseImpl(
    private val authRepository: AuthRepository,
) : SaveUserIdUseCase {

    override suspend fun invoke(id: Long) {
        return authRepository.saveUserId(id)
    }
}
