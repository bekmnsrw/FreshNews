package kfu.itis.freshnews.android.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.R
import kfu.itis.freshnews.android.designsystem.icon.FreshNewsIcons
import kfu.itis.freshnews.android.designsystem.theme.ThemeProvider
import kfu.itis.freshnews.android.utils.ColumnSpacer
import kfu.itis.freshnews.android.widget.FreshNewsButton
import kfu.itis.freshnews.android.widget.FreshNewsIconButton
import kfu.itis.freshnews.android.widget.FreshNewsOutlinedButton
import kfu.itis.freshnews.android.widget.FreshNewsTextButton
import kfu.itis.freshnews.feature.auth.presentation.AuthEvent
import kfu.itis.freshnews.feature.auth.presentation.AuthState

@Composable
fun AuthView(
    state: AuthState,
    eventHandler: (AuthEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(ThemeProvider.colors.error),
        content = { paddingValues ->
            AuthContent(
                scaffoldPadding = paddingValues,
                state = state,
                eventHandler = eventHandler,
            )
        },
        containerColor = ThemeProvider.colors.background,
    )
}

@Composable
private fun AuthContent(
    scaffoldPadding: PaddingValues,
    state: AuthState,
    eventHandler: (AuthEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(scaffoldPadding)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ColumnSpacer(22.dp)

        WelcomeLabel()

        ColumnSpacer(16.dp)

        LoginField(
            login = state.login,
            onLoginChange = { login -> eventHandler(AuthEvent.OnLoginChange(login)) },
            isSignUpErrorShown = state.isSignUpErrorShown,
            isSignInErrorShown = state.isSignInErrorShown,
            isTextFieldEmpty = state.isEmptyLogin,
        )

        PasswordField(
            password = state.password,
            isPasswordHidden = state.isPasswordHidden,
            onPasswordChange = { password -> eventHandler(AuthEvent.OnPasswordChange(password)) },
            onHidePasswordClick = { eventHandler(AuthEvent.OnHidePasswordClick) },
            isTextFieldEmpty = state.isEmptyPassword,
        )

        ColumnSpacer(16.dp)

        SignInButton(
            onClick = { eventHandler(AuthEvent.OnSignInClick) },
            isEnabled = !state.isEmptyLogin && !state.isEmptyPassword,
        )

        SingUpButton(
            onClick = { eventHandler(AuthEvent.OnSignUpClick) },
            isEnabled = !state.isEmptyLogin && !state.isEmptyPassword,
        )

        ColumnSpacer(4.dp)

        SkipAuthButton { eventHandler(AuthEvent.OnSkipAuthClick) }
    }
}

@Composable
private fun WelcomeLabel() {
    Text(
        text = stringResource(R.string.welcome),
        color = ThemeProvider.colors.accent,
        style = ThemeProvider.typography.screenHeading,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginField(
    login: String,
    onLoginChange: (String) -> Unit,
    isSignInErrorShown: Boolean,
    isSignUpErrorShown: Boolean,
    isTextFieldEmpty: Boolean,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = login,
        onValueChange = { loginInput -> onLoginChange(loginInput) },
        label = { Text(stringResource(R.string.login_placeholder)) },
        isError = isSignUpErrorShown || isSignInErrorShown,
        singleLine = true,
        supportingText = {
            if (isSignUpErrorShown || isSignInErrorShown || isTextFieldEmpty) {
                Text(
                    text = when {
                        isSignInErrorShown -> stringResource(R.string.incorrect_login)
                        isSignUpErrorShown -> stringResource(R.string.user_already_exists)
                        isTextFieldEmpty -> stringResource(R.string.empty_login)
                        else -> ""
                    },
                    color = ThemeProvider.colors.accent,
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = ThemeProvider.colors.accent,
            focusedLabelColor = ThemeProvider.colors.accent,
            cursorColor = ThemeProvider.colors.accent,
            focusedTextColor = ThemeProvider.colors.mainText,
            unfocusedTextColor = ThemeProvider.colors.mainText,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(
    isPasswordHidden: Boolean,
    onHidePasswordClick: () -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isTextFieldEmpty: Boolean,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = { passwordInput -> onPasswordChange(passwordInput) },
        label = { Text(stringResource(R.string.password_placeholder)) },
        visualTransformation = if (isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            FreshNewsIconButton(
                icon = if (isPasswordHidden) FreshNewsIcons.VISIBILITY else FreshNewsIcons.VISIBILITY_OFF,
                tint = ThemeProvider.colors.accent,
                onClick = onHidePasswordClick,
            )
        },
        supportingText = {
            if (isTextFieldEmpty) {
                Text(
                    text = stringResource(R.string.empty_password),
                    color = ThemeProvider.colors.error,
                )
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = ThemeProvider.colors.accent,
            focusedLabelColor = ThemeProvider.colors.accent,
            cursorColor = ThemeProvider.colors.accent,
            focusedTextColor = ThemeProvider.colors.mainText,
            unfocusedTextColor = ThemeProvider.colors.mainText,
            errorTextColor = ThemeProvider.colors.mainText,
        )
    )
}

@Composable
private fun SignInButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
) {
    FreshNewsButton(
        text = stringResource(R.string.sign_in),
        containerColor = ThemeProvider.colors.buttonContainer,
        contentColor = ThemeProvider.colors.buttonContent,
        onClick = onClick,
        isEnabled = isEnabled,
    )
}

@Composable
private fun SingUpButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
) {
    FreshNewsOutlinedButton(
        text = stringResource(R.string.sign_up),
        containerColor = ThemeProvider.colors.buttonContainer,
        contentColor = ThemeProvider.colors.buttonContent,
        isEnabled = isEnabled,
        onClick = onClick,
    )
}

@Composable
private fun SkipAuthButton(onClick: () -> Unit) {
    FreshNewsTextButton(
        text = stringResource(R.string.skip_for_now),
        textColor = ThemeProvider.colors.outline,
        onClick = onClick,
    )
}
