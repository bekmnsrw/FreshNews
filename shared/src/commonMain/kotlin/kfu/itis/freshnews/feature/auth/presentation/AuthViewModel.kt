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
        is AuthEvent.OnSignInClick -> onSignInClick()
        is AuthEvent.OnSignUpClick -> onSignUpClick()
        AuthEvent.OnSkipAuthClick -> onSkipAuthClick()
    }

    init {
        logOpenScreen()
    }

    private fun onHidePasswordClick() {
        state = state.copy(isPasswordHidden = !state.isPasswordHidden)
    }

    private fun onLoginChange(login: String) {
        state = state.copy(
            login = login,
            isEmptyLogin = login.isBlank(),
        )
    }

    private fun onPasswordChange(password: String) {
        state = state.copy(
            password = password,
            isEmptyPassword = password.isBlank(),
        )
    }

    private fun onSignInClick() {
        scope.launch {
            try {
                if (areTextFieldsEmpty()) return@launch
                val userProfile = signInUseCase(state.login, state.password).first()
                if (userProfile == null) {
                    state = state.copy(
                        isSignInErrorShown = true,
                        login = "",
                        password = "",
                    )
                } else {
                    userProfile.id?.let { userId ->
                        saveUserIdUseCase(userId)
                        firebaseAnalyticsBinding.logSignIn(state.login)
                        action = AuthAction.NavigateHome
                    }
                }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onSignUpClick() {
        scope.launch {
            try {
                if (areTextFieldsEmpty()) return@launch
                val userProfile = signUpUseCase(state.login, state.password).first()
                if (userProfile == null) {
                    state = state.copy(
                        isSignUpErrorShown = true,
                        login = "",
                        password = "",
                    )
                } else {
                    userProfile.id?.let { userId ->
                        saveUserIdUseCase(userId)
                        firebaseAnalyticsBinding.logSignUp(state.login)
                        action = AuthAction.NavigateHome
                    }
                }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun areTextFieldsEmpty(): Boolean {
        state = state.copy(
            isEmptyLogin = state.login.isBlank(),
            isEmptyPassword = state.password.isBlank(),
        )
        return state.isEmptyLogin || state.isEmptyPassword
    }

    private fun onSkipAuthClick() {
        action = AuthAction.NavigateHome
    }

    private fun logOpenScreen() {
        firebaseAnalyticsBinding.logOpenScreen("auth_screen")
    }

    private fun handleError(e: Throwable) {
        action = AuthAction.ShowError
        firebaseCrashlyticsBinding.sendNonFatalErrorReport(e)
    }
}
