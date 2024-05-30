package kfu.itis.freshnews.feature.auth.presentation

import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.core.firebase.FirebaseAnalyticsBinding
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.core.viewmodel.BaseViewModel
import kfu.itis.freshnews.feature.auth.domain.usecase.SaveUserIdUseCase
import kfu.itis.freshnews.feature.auth.domain.usecase.SignInUseCase
import kfu.itis.freshnews.feature.auth.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AuthViewModel : BaseViewModel<AuthState, AuthAction, AuthEvent>(
    initialState = AuthState(),
) {

    private val firebaseCrashlyticsBinding: FirebaseCrashlyticsBinding by PlatformSDK.lazyInstance()
    private val firebaseAnalyticsBinding: FirebaseAnalyticsBinding by PlatformSDK.lazyInstance()
    private val signInUseCase: SignInUseCase by PlatformSDK.lazyInstance()
    private val signUpUseCase: SignUpUseCase by PlatformSDK.lazyInstance()
    private val saveUserIdUseCase: SaveUserIdUseCase by PlatformSDK.lazyInstance()

    override fun handleEvent(event: AuthEvent) = when (event) {
        AuthEvent.OnHidePasswordClick -> onHidePasswordClick()
        is AuthEvent.OnLoginChange -> onLoginChange(event.login)
        is AuthEvent.OnPasswordChange -> onPasswordChange(event.password)
        is AuthEvent.OnSignInClick -> onSignInClick(event.login, event.password)
        is AuthEvent.OnSignUpClick -> onSignUpClick(event.login, event.password)
        AuthEvent.OnSkipAuthClick -> onSkipAuthClick()
    }

    init {
        logOpenScreen()
    }

    private fun onHidePasswordClick() {

    }

    private fun onLoginChange(login: String) {
        state = state.copy(login = login)
    }

    private fun onPasswordChange(password: String) {
        state = state.copy(password = password)
    }

    private fun onSignInClick(login: String, password: String) {
        scope.launch {
            try {
                val userProfile = signInUseCase(login, password).first()
                if (userProfile == null) {
                    state = state.copy(
                        isSignInErrorShown = true,
                        login = "",
                        password = "",
                    )
                } else {
                    userProfile.id?.let { userId ->
                        saveUserIdUseCase(userId)
                        firebaseAnalyticsBinding.logSignIn(login)
                        action = AuthAction.NavigateHome
                    }
                }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onSignUpClick(login: String, password: String) {
        scope.launch {
            try {
                val userProfile = signUpUseCase(login, password).first()
                if (userProfile == null) {
                    state = state.copy(
                        isSignUpErrorShown = true,
                        login = "",
                        password = "",
                    )
                } else {
                    userProfile.id?.let { userId ->
                        saveUserIdUseCase(userId)
                        firebaseAnalyticsBinding.logSignUp(login)
                        action = AuthAction.NavigateHome
                    }
                }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onSkipAuthClick() {
        action = AuthAction.NavigateHome
    }

    private fun logOpenScreen() {
        firebaseAnalyticsBinding.logOpenScreen("auth_screen")
    }

    private fun handleError(e: Throwable) {
        action = AuthAction.ShowError("")
        firebaseCrashlyticsBinding.sendNonFatalErrorReport(e)
    }
}
