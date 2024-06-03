package kfu.itis.freshnews.feature.profile.presentation

import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.core.firebase.FirebaseAnalyticsBinding
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.core.viewmodel.BaseViewModel
import kfu.itis.freshnews.feature.auth.domain.usecase.GetUserIdUseCase
import kfu.itis.freshnews.feature.auth.domain.usecase.LogOutUseCase
import kfu.itis.freshnews.feature.profile.domain.usecase.ChangeDarkModeUseCase
import kfu.itis.freshnews.feature.profile.domain.usecase.DeleteProfileUseCase
import kfu.itis.freshnews.feature.profile.domain.usecase.GetProfileUseCase
import kfu.itis.freshnews.feature.profile.domain.usecase.IsDarkModeEnabledUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileViewModel : BaseViewModel<ProfileState, ProfileAction, ProfileEvent>(
    initialState = ProfileState(),
) {

    private val firebaseCrashlyticsBinding: FirebaseCrashlyticsBinding by PlatformSDK.lazyInstance()
    private val firebaseAnalyticsBinding: FirebaseAnalyticsBinding by PlatformSDK.lazyInstance()
    private val getUserIdUseCase: GetUserIdUseCase by PlatformSDK.lazyInstance()
    private val logOutUseCase: LogOutUseCase by PlatformSDK.lazyInstance()
    private val getProfileUseCase: GetProfileUseCase by PlatformSDK.lazyInstance()
    private val deleteProfileUseCase: DeleteProfileUseCase by PlatformSDK.lazyInstance()
    private val isDarkModeEnabledUseCase: IsDarkModeEnabledUseCase by PlatformSDK.lazyInstance()
    private val changeDarkModeUseCase: ChangeDarkModeUseCase by PlatformSDK.lazyInstance()

    init {
        logOpenScreen()
        isDarkModeEnabled()
        loadProfile()
    }

    override fun handleEvent(event: ProfileEvent) = when (event) {
        ProfileEvent.OnDeleteAccountClick -> onDeleteAccountClick()
        ProfileEvent.OnAuthenticateClick -> onAuthenticateClick()
        ProfileEvent.OnLogOutClick -> onLogOutClick()
        is ProfileEvent.OnDialogConfirm -> onDialogConfirm(event.dialogType)
        is ProfileEvent.OnDialogDismiss -> onDialogDismiss()
    }

    private fun onDeleteAccountClick() {
        state = state.copy(
            isDialogShown = true,
            shownDialogType = DialogType.PROFILE_DELETION,
        )
    }

    private fun onAuthenticateClick() {
        action = ProfileAction.NavigateAuth
    }

    private fun onDarkModeChanged() {
        scope.launch {
            try {
                changeDarkModeUseCase(!state.isDarkModeEnabled)
                state = state.copy(isDarkModeEnabled = !state.isDarkModeEnabled)
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onLogOutClick() {
        state = state.copy(
            isDialogShown = true,
            shownDialogType = DialogType.LOGOUT,
        )
    }

    private fun loadProfile() {
        scope.launch {
            try {
                val userId = getUserIdUseCase()
                if (userId == null) {
                    state = state.copy(isUserAuthenticated = false)
                } else {
                    val userProfile = getProfileUseCase(userId).first()
                    state = state.copy(
                        isUserAuthenticated = true,
                        profile = userProfile,
                    )
                }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onDialogDismiss() {
        state = state.copy(isDialogShown = false)
    }

    private fun onDialogConfirm(dialogType: DialogType) {
        scope.launch {
            try {
                when (dialogType) {
                    DialogType.LOGOUT -> {
                        logOutUseCase()
                        state = state.copy(
                            isUserAuthenticated = false,
                            profile = null,
                            isDialogShown = false,
                        )
                    }
                    DialogType.PROFILE_DELETION -> {
                        val userId = getUserIdUseCase()
                        userId?.let { id ->
                            deleteProfileUseCase(id)
                            logOutUseCase()
                            state = state.copy(
                                isUserAuthenticated = false,
                                profile = null,
                                isDialogShown = false,
                            )
                        }
                    }
                }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun logOpenScreen() {
        firebaseAnalyticsBinding.logOpenScreen("profile_screen")
    }

    private fun isDarkModeEnabled() {
        scope.launch {
            try {
                val isDarkModeEnabled = isDarkModeEnabledUseCase()
                state = state.copy(isDarkModeEnabled = isDarkModeEnabled)
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun handleError(e: Throwable) {
        state = state.copy(error = e)
        action = ProfileAction.ShowError
        firebaseCrashlyticsBinding.sendNonFatalErrorReport(e)
    }
}
