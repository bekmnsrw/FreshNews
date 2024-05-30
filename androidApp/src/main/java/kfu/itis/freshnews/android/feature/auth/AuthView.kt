package kfu.itis.freshnews.android.feature.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.theme.FreshNewsIcons
import kfu.itis.freshnews.android.theme.ThemeProvider
import kfu.itis.freshnews.android.utils.ColumnSpacer
import kfu.itis.freshnews.android.widget.FreshNewsIconButton
import kfu.itis.freshnews.feature.auth.presentation.AuthEvent
import kfu.itis.freshnews.feature.auth.presentation.AuthState

@Composable
fun AuthView(
    state: AuthState,
    eventHandler: (AuthEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            AuthContent(
                scaffoldPadding = paddingValues,
                state = state,
                eventHandler = eventHandler,
            )
        }
    )
}

@Composable
private fun AuthContent(
    scaffoldPadding: PaddingValues,
    state: AuthState,
    eventHandler: (AuthEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
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
                isTextFieldEmpty = state.isEmptyLogin,
            )

            ColumnSpacer(16.dp)

            SignInButton(
                onClick = { eventHandler(AuthEvent.OnSignInClick) },
                isEnabled = !state.isEmptyLogin,
            )

            SingUpButton(
                onClick = { eventHandler(AuthEvent.OnSignUpClick) },
                isEnabled = !state.isEmptyPassword,
            )

            ColumnSpacer(4.dp)

            SkipAuthButton(
                onClick = { eventHandler(AuthEvent.OnSkipAuthClick) },
            )
        }
    }
}

@Composable
private fun WelcomeLabel() {
    Text(
        text = "Welcome to FreshNews",
        color = ThemeProvider.colors.accent,
        style = ThemeProvider.typography.welcomeTitle,
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
        label = { Text(text = "Login") },
        isError = isSignUpErrorShown || isSignInErrorShown,
        singleLine = true,
        supportingText = {
            if (isSignUpErrorShown || isSignInErrorShown || isTextFieldEmpty) {
                Text(
                    text = when {
                        isSignInErrorShown -> "Incorrect login"
                        isSignUpErrorShown -> "User with this login already exists"
                        isTextFieldEmpty -> "Login mustn't be empty"
                        else -> ""
                    },
                    color = ThemeProvider.colors.errorColor,
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = ThemeProvider.colors.accent,
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
        label = { Text(text = "Password") },
        visualTransformation = PasswordVisualTransformation(),
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
                    text = "Password mustn't be empty",
                    color = ThemeProvider.colors.errorColor,
                )
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = ThemeProvider.colors.accent,
        )
    )
}

@Composable
private fun SignInButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ThemeProvider.colors.accent,
        ),
        shape = RoundedCornerShape(8.dp),
        enabled = isEnabled,
    ) {
        Text(
            text = "Sign In",
            color = ThemeProvider.colors.primary,
        )
    }
}

@Composable
private fun SingUpButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
) {
    OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        border = BorderStroke(1.dp, ThemeProvider.colors.accent),
        shape = RoundedCornerShape(8.dp),
        enabled = isEnabled,
    ) {
        Text(
            text = "Sign Up",
            color = ThemeProvider.colors.accent,
        )
    }
}

@Composable
private fun SkipAuthButton(
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        Text(
            text = "Skip for now",
            color = ThemeProvider.colors.onPrimaryVariant,
        )
    }
}
