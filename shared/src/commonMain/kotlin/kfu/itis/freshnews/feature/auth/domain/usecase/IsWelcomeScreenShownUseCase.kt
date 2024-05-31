package kfu.itis.freshnews.feature.auth.domain.usecase

interface IsWelcomeScreenShownUseCase {

    suspend operator fun invoke(): Boolean
}
