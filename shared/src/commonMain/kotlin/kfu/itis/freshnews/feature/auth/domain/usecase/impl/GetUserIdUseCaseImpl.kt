package kfu.itis.freshnews.feature.auth.domain.usecase.impl

import kfu.itis.freshnews.feature.auth.domain.AuthRepository
import kfu.itis.freshnews.feature.auth.domain.usecase.GetUserIdUseCase

internal class GetUserIdUseCaseImpl(
    private val authRepository: AuthRepository,
) : GetUserIdUseCase {

    override suspend fun invoke(): Long? {
        return authRepository.getUserId()
    }
}
