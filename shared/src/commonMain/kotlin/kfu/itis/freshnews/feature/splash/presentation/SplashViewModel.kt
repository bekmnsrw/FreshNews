package kfu.itis.freshnews.feature.splash.presentation

import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.core.viewmodel.BaseViewModel
import kfu.itis.freshnews.feature.auth.domain.usecase.IsWelcomeScreenShownUseCase
import kfu.itis.freshnews.feature.auth.domain.usecase.SetWelcomeScreenShownUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel<SplashState, SplashAction, SplashEvent>(
    initialState = SplashState(),
) {

    private val isWelcomeScreenShownUseCase: IsWelcomeScreenShownUseCase by PlatformSDK.lazyInstance()
    private val setWelcomeScreenShownUseCase: SetWelcomeScreenShownUseCase by PlatformSDK.lazyInstance()

    override fun handleEvent(event: SplashEvent) {}

    init {
        checkIfWelcomeScreenAlreadyShown()
    }

    private fun checkIfWelcomeScreenAlreadyShown() {
        scope.launch {
            state = state.copy(isLoading = true)
            val isShown = isWelcomeScreenShownUseCase()
            if (isShown) {
                action = SplashAction.NavigateHome
            } else {
                setWelcomeScreenShownUseCase()
                action = SplashAction.NavigateAuth
            }
            delay(3000L)
            state = state.copy(isLoading = false)
        }
    }
}
