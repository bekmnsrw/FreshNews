package kfu.itis.freshnews.feature.profile.domain.usecase

interface IsDarkModeEnabledUseCase {

    suspend operator fun invoke(): Boolean
}
