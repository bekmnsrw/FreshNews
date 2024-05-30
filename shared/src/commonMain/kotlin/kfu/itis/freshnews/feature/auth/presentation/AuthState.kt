package kfu.itis.freshnews.feature.auth.presentation

data class AuthState(
    val login: String = "",
    val password: String = "",
    val isSignInErrorShown: Boolean = false,
    val isSignUpErrorShown: Boolean = false,
    val isPasswordHidden: Boolean = true,
)

sealed class AuthEvent {
    class OnLoginChange(val login: String) : AuthEvent()
    class OnPasswordChange(val password: String) : AuthEvent()
    class OnSignUpClick(val login: String, val password: String) : AuthEvent()
    class OnSignInClick(val login: String, val password: String) : AuthEvent()
    object OnSkipAuthClick : AuthEvent()
    object OnHidePasswordClick : AuthEvent()
}

sealed class AuthAction {
    object NavigateHome : AuthAction()
    class ShowError(val errorMessage: String) : AuthAction()
}
