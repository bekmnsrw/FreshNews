package kfu.itis.freshnews.feature.auth.presentation

data class AuthState(
    val login: String = "",
    val password: String = "",
    val isSignInErrorShown: Boolean = false,
    val isSignUpErrorShown: Boolean = false,
    val isPasswordHidden: Boolean = true,
    val isEmptyLogin: Boolean = false,
    val isEmptyPassword: Boolean = false,
)

sealed class AuthEvent {
    class OnLoginChange(val login: String) : AuthEvent()
    class OnPasswordChange(val password: String) : AuthEvent()
    object OnSignUpClick : AuthEvent()
    object OnSignInClick : AuthEvent()
    object OnSkipAuthClick : AuthEvent()
    object OnHidePasswordClick : AuthEvent()
}

sealed class AuthAction {
    object NavigateHome : AuthAction()
    object ShowError : AuthAction()
}
