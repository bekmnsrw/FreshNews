package kfu.itis.freshnews.feature.auth.domain.usecase

interface GetUserIdUseCase {

    suspend operator fun invoke(): Int?
}
