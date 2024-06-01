package kfu.itis.freshnews.android.designsystem.theme

import androidx.compose.runtime.Composable
import kfu.itis.freshnews.android.designsystem.color.FreshNewsColors
import kfu.itis.freshnews.android.designsystem.color.LocalColors
import kfu.itis.freshnews.android.designsystem.typography.FreshNewsTypography
import kfu.itis.freshnews.android.designsystem.typography.LocalTypography

object ThemeProvider {

    val typography: FreshNewsTypography
        @Composable
        get() = LocalTypography.current

    val colors: FreshNewsColors
        @Composable
        get() = LocalColors.current
}
