package kfu.itis.freshnews.feature.profile.presentation

import kfu.itis.freshnews.feature.profile.domain.model.UserProfile

data class ProfileState(
    val isUserAuthenticated: Boolean = false,
    val profile: UserProfile? = null,
    val isDarkModeEnabled: Boolean = false,
    val error: Throwable? = null,
    val isDialogShown: Boolean = false,
    val shownDialogType: DialogType = DialogType.LOGOUT,
)

sealed class ProfileEvent {
    object OnAuthenticateClick : ProfileEvent()
    object OnLogOutClick : ProfileEvent()
    object OnDeleteAccountClick : ProfileEvent()
    class OnDialogConfirm(val dialogType: DialogType) : ProfileEvent()
    object OnDialogDismiss : ProfileEvent()
    object OnInit : ProfileEvent()
}

sealed class ProfileAction {
    object NavigateAuth : ProfileAction()
    object ShowError : ProfileAction()
}

enum class DialogType {

    LOGOUT,
    PROFILE_DELETION,
}
