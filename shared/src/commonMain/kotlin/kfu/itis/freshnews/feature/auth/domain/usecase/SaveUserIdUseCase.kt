package kfu.itis.freshnews.feature.auth.domain.usecase

interface SaveUserIdUseCase {

    suspend operator fun invoke(id: Int)
}
