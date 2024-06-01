package kfu.itis.freshnews.android.app

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.feature.profile.domain.usecase.IsDarkModeEnabledUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppSettings : ViewModel() {

    private val _state = MutableStateFlow(AppSettingsState())
    val state: StateFlow<AppSettingsState> = _state.asStateFlow()

    private val isDarkModeEnabledUseCase: IsDarkModeEnabledUseCase by PlatformSDK.lazyInstance()

    fun eventHandler(event: AppSettingsEvent) = when (event) {
        is AppSettingsEvent.ChangeDarkModeEnabled -> changeDarkModeEnabled()
    }

    init {
        checkIsDarkModeEnabled()
    }

    private fun checkIsDarkModeEnabled() {
        viewModelScope.launch {
            val isDarkModeEnabled = isDarkModeEnabledUseCase()
            _state.emit(
                _state.value.copy(
                    isDarkModeEnabled = isDarkModeEnabled,
                )
            )
        }
    }

    private fun changeDarkModeEnabled() {
        viewModelScope.launch {
            val isDarkModeEnabled = _state.value.isDarkModeEnabled
            _state.emit(
                _state.value.copy(
                    isDarkModeEnabled = !isDarkModeEnabled,
                )
            )
        }
    }
}

@Immutable
data class AppSettingsState(
    val isDarkModeEnabled: Boolean = false,
)

@Immutable
sealed class AppSettingsEvent {
    object ChangeDarkModeEnabled : AppSettingsEvent()
}
