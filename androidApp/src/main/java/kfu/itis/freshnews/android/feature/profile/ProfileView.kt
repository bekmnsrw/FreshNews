package kfu.itis.freshnews.android.feature.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.R
import kfu.itis.freshnews.android.designsystem.theme.ThemeProvider
import kfu.itis.freshnews.android.utils.ColumnSpacer
import kfu.itis.freshnews.android.widget.FreshNewsButton
import kfu.itis.freshnews.android.widget.FreshNewsOutlinedButton
import kfu.itis.freshnews.android.widget.FreshNewsTextButton
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
                onAuthenticateClick = { eventHandler(ProfileEvent.OnAuthenticateClick) },
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
        },
        containerColor = ThemeProvider.colors.background,
    )
}

@Composable
private fun ProfileContent(
    scaffoldPadding: PaddingValues,
    isUserAuthenticated: Boolean,
    login: String?,
    onAuthenticateClick: () -> Unit,
    onLogOutClick: () -> Unit,
    onDeleteProfileClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
    ) {
        Column(Modifier.padding(horizontal = 24.dp)) {
            ProfileTitle(login)
            ColumnSpacer(16.dp)
            if (!isUserAuthenticated) {
                AuthenticateButton(onAuthenticateClick)
            }
            if (isUserAuthenticated) {
                LogOutButton(onLogOutClick)
                DeleteProfileButton(onDeleteProfileClick)
            }
        }
    }
}

@Composable
private fun ProfileTitle(
    login: String?,
) {
    val visibleText = login ?: stringResource(R.string.dear_guest)
    Text(
        text = stringResource(R.string.hello, visibleText),
        style = ThemeProvider.typography.screenHeading,
        color = ThemeProvider.colors.mainText,
    )
}

@Composable
private fun AuthenticateButton(
    onClick: () -> Unit,
) {
    FreshNewsButton(
        text = stringResource(R.string.sign_in),
        containerColor = ThemeProvider.colors.buttonContainer,
        contentColor = ThemeProvider.colors.buttonContent,
        onClick = onClick,
    )
}

@Composable
private fun LogOutButton(onClick: () -> Unit) {
    FreshNewsOutlinedButton(
        text = stringResource(R.string.log_out),
        containerColor = ThemeProvider.colors.error,
        contentColor = ThemeProvider.colors.error,
        onClick = onClick,
    )
}

@Composable
private fun DeleteProfileButton(onClick: () -> Unit) {
    FreshNewsButton(
        text = stringResource(R.string.delete_profile),
        containerColor = ThemeProvider.colors.error,
        contentColor = ThemeProvider.colors.buttonContent,
        onClick = onClick,
    )
}

@Composable
private fun ConfirmationDialog(
    dialogType: DialogType,
    onDismiss: () -> Unit,
    onConfirm: (DialogType) -> Unit,
) {
    AlertDialog(
        containerColor = ThemeProvider.colors.bottomBar,
        textContentColor = ThemeProvider.colors.mainText,
        titleContentColor = ThemeProvider.colors.mainText,
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = when (dialogType) {
                    DialogType.LOGOUT -> stringResource(R.string.log_out_confirmation)
                    DialogType.PROFILE_DELETION -> stringResource(R.string.delete_profile_confirmation)
                },
            )
        },
        confirmButton = {
            FreshNewsTextButton(
                text = stringResource(R.string.confirm),
                textColor = ThemeProvider.colors.accent,
                onClick = { onConfirm(dialogType) },
            )
        },
        dismissButton = {
            FreshNewsTextButton(
                text = stringResource(R.string.dismiss),
                textColor = ThemeProvider.colors.outline,
                onClick = onDismiss,
            )
        }
    )
}
