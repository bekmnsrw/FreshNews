package kfu.itis.freshnews.feature.profile.presentation

import kfu.itis.freshnews.feature.profile.domain.model.UserProfile

data class ProfileState(
    val isUserAuthenticated: Boolean = false,
    val profile: UserProfile? = null,
    val isDarkModeEnabled: Boolean = false,
    val error: Throwable? = null,
)

sealed class ProfileEvent {
    object OnAuthenticateClick : ProfileEvent()
    object OnDarkModeClick : ProfileEvent()
    object OnLogOutClick : ProfileEvent()
    object OnDeleteAccountClick : ProfileEvent()
}

sealed class ProfileAction {
    object NavigateAuth : ProfileAction()
    class ShowError(val errorMessage: String) : ProfileAction()
}
