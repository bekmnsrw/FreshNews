package kfu.itis.freshnews.android.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import kfu.itis.freshnews.android.designsystem.icon.FreshNewsIcons
import kfu.itis.freshnews.android.designsystem.theme.ThemeProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreshNewsTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    shouldShowSupportingText: Boolean,
    supportingText: String,
    isPassword: Boolean = false,
    isPasswordHidden: Boolean = false,
    onTrailingIconClick: () -> Unit = {},
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = isError,
        singleLine = true,
        supportingText = {
            if (shouldShowSupportingText) {
                Text(
                    text = supportingText,
                    color = ThemeProvider.colors.error,
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = ThemeProvider.colors.accent,
            focusedLabelColor = ThemeProvider.colors.accent,
            cursorColor = ThemeProvider.colors.accent,
            focusedTextColor = ThemeProvider.colors.mainText,
            unfocusedTextColor = ThemeProvider.colors.mainText,
            errorTextColor = ThemeProvider.colors.mainText,
        ),
        visualTransformation = if (isPasswordHidden) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = if (isPassword) {
            KeyboardOptions(keyboardType = KeyboardType.Password)
        } else {
            KeyboardOptions.Default
        },
        trailingIcon = {
            if (isPassword) {
                FreshNewsIconButton(
                    icon = if (isPasswordHidden) FreshNewsIcons.VISIBILITY else FreshNewsIcons.VISIBILITY_OFF,
                    tint = ThemeProvider.colors.accent,
                    onClick = onTrailingIconClick,
                )
            }
        }
    )
}
