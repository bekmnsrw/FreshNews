package kfu.itis.freshnews.android.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.designsystem.theme.ThemeProvider

@Composable
fun FreshNewsButton(
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color,
    contentColor: Color,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContentColor = Color.White,
            disabledContainerColor = ThemeProvider.colors.outline,
        ),
        enabled = isEnabled,
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = text,
            style = ThemeProvider.typography.button,
        )
    }
}

@Composable
fun FreshNewsOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color,
    contentColor: Color,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        border = BorderStroke(
            width = 1.dp,
            color = if (isEnabled) containerColor else ThemeProvider.colors.outline,
        ),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = ThemeProvider.colors.background,
            disabledContentColor = ThemeProvider.colors.outline,
            disabledContainerColor = ThemeProvider.colors.background,
        ),
        enabled = isEnabled,
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = text,
            style = ThemeProvider.typography.button,
        )
    }
}

@Composable
fun FreshNewsTextButton(
    text: String,
    textColor: Color,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            color = textColor,
            style = ThemeProvider.typography.button,
        )
    }
}
