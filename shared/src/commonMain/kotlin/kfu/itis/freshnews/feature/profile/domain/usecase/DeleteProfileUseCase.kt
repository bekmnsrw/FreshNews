package kfu.itis.freshnews.feature.profile.domain.usecase

interface DeleteProfileUseCase {

    suspend operator fun invoke(id: Long)
}
