package kfu.itis.freshnews.android.feature.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.theme.FreshNewsIcons
import kfu.itis.freshnews.android.theme.ThemeProvider
import kfu.itis.freshnews.feature.profile.presentation.DialogType
import kfu.itis.freshnews.feature.profile.presentation.ProfileEvent
import kfu.itis.freshnews.feature.profile.presentation.ProfileState

@Composable
fun ProfileView(
    state: ProfileState,
    eventHandler: (ProfileEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            ProfileContent(
                scaffoldPadding = paddingValues,
                isUserAuthenticated = state.isUserAuthenticated,
                login = state.profile?.login,
                isDarkModeEnabled = state.isDarkModeEnabled,
                onAuthenticateClick = { eventHandler(ProfileEvent.OnAuthenticateClick) },
                onDarkModeChanged = { eventHandler(ProfileEvent.OnDarkModeChanged) },
                onLogOutClick = { eventHandler(ProfileEvent.OnLogOutClick) },
                onDeleteProfileClick = { eventHandler(ProfileEvent.OnDeleteAccountClick) },
            )
            if (state.isDialogShown) {
                ConfirmationDialog(
                    dialogType = state.shownDialogType,
                    onDismiss = { eventHandler(ProfileEvent.OnDialogDismiss) },
                    onConfirm = { dialogType -> eventHandler(ProfileEvent.OnDialogConfirm(dialogType)) },
                )
            }
        }
    )
}

@Composable
private fun ProfileContent(
    scaffoldPadding: PaddingValues,
    isUserAuthenticated: Boolean,
    login: String?,
    isDarkModeEnabled: Boolean,
    onAuthenticateClick: () -> Unit,
    onDarkModeChanged: () -> Unit,
    onLogOutClick: () -> Unit,
    onDeleteProfileClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
    ) {
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            ProfileTitle(
                login = login,
            )
            DarkThemeSwitch(
                isDarkModeEnabled = isDarkModeEnabled,
                onCheckedChange = onDarkModeChanged,
            )
            if (!isUserAuthenticated) {
                AuthenticateButton(
                    onClick = onAuthenticateClick,
                )
            }
            if (isUserAuthenticated) {
                LogOutButton(
                    onClick = onLogOutClick,
                )
                DeleteProfileButton(
                    onClick = onDeleteProfileClick,
                )
            }
        }
    }
}

@Composable
private fun ProfileTitle(
    login: String?,
) {
    val visibleText = login ?: "Dear Guest"
    Text(
        text = "Hello, $visibleText!",
        style = ThemeProvider.typography.screenHeadline,
    )
}

@Composable
private fun DarkThemeSwitch(
    isDarkModeEnabled: Boolean,
    onCheckedChange: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Dark mode",
        )
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Switch(
                checked = isDarkModeEnabled,
                onCheckedChange = { onCheckedChange() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = ThemeProvider.colors.primary,
                    checkedTrackColor = ThemeProvider.colors.accent,
                    uncheckedThumbColor = ThemeProvider.colors.accent,
                    uncheckedTrackColor = ThemeProvider.colors.outline,
                    checkedBorderColor = ThemeProvider.colors.accent,
                    uncheckedBorderColor = ThemeProvider.colors.accent,
                ),
                thumbContent = {
                    if (isDarkModeEnabled) {
                        Icon(
                            imageVector = FreshNewsIcons.DONE,
                            contentDescription = null,
                            tint = ThemeProvider.colors.accent,
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun AuthenticateButton(
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ThemeProvider.colors.accent,
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = "Log In",
            color = ThemeProvider.colors.primary,
        )
    }
}

@Composable
private fun LogOutButton(
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        border = BorderStroke(1.dp, ThemeProvider.colors.errorColor),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = "Log Out",
            color = ThemeProvider.colors.errorColor,
        )
    }
}

@Composable
private fun DeleteProfileButton(
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ThemeProvider.colors.errorColor,
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = "Delete profile",
            color = ThemeProvider.colors.primary,
        )
    }
}

@Composable
private fun ConfirmationDialog(
    dialogType: DialogType,
    onDismiss: () -> Unit,
    onConfirm: (DialogType) -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = when (dialogType) {
                    DialogType.LOGOUT -> "Do you want to Log Out?"
                    DialogType.PROFILE_DELETION -> "Do you want to delete your profile"
                }
            )
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(dialogType) }) {
                Text(
                    text = "Confirm",
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Dismiss",
                )
            }
        }
    )
}
