package kfu.itis.freshnews.feature.profile.domain.usecase

interface ChangeDarkModeUseCase {

    suspend operator fun invoke(isDarkModeEnabled: Boolean)
}
