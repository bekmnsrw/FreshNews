package kfu.itis.freshnews.feature.splash.presentation

data class SplashState(
    val isLoading: Boolean = false,
    val isWelcomeScreenShown: Boolean = false,
)

sealed class SplashEvent

sealed class SplashAction {
    object NavigateHome : SplashAction()
    object NavigateAuth : SplashAction()
}
