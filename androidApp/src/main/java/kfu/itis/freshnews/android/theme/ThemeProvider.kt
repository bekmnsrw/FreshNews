package kfu.itis.freshnews.android.theme

import androidx.compose.runtime.Composable

object ThemeProvider {

    val typography: FreshNewsTypography
        @Composable get() = LocalTypography.current

    val colors: FreshNewsColors
        @Composable get() = LocalColors.current
}
