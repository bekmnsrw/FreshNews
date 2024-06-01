package kfu.itis.freshnews.android.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kfu.itis.freshnews.android.designsystem.color.FreshNewsColors
import kfu.itis.freshnews.android.designsystem.color.LocalColors
import kfu.itis.freshnews.android.designsystem.typography.FreshNewsTypography
import kfu.itis.freshnews.android.designsystem.typography.LocalTypography

@Composable
fun FreshNewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    SystemUiColors(
        systemUiController = rememberSystemUiController(),
        darkTheme = darkTheme,
    )

    CompositionLocalProvider(
        LocalTypography provides FreshNewsTypography(),
        LocalColors provides when (darkTheme) {
            true -> FreshNewsColors.FreshNewsDarkThemeColors()
            false -> FreshNewsColors.FreshNewsLightThemeColors()
        },
        content = content,
    )
}

@Composable
private fun SystemUiColors(
    systemUiController: SystemUiController,
    darkTheme: Boolean
) {
    SideEffect {
        systemUiController.setStatusBarColor(
            color = when (darkTheme) {
                true -> FreshNewsColors.FreshNewsDarkThemeColors().background
                false -> FreshNewsColors.FreshNewsLightThemeColors().background
            },
            darkIcons = !darkTheme
        )
        systemUiController.setNavigationBarColor(
            color = when (darkTheme) {
                true -> FreshNewsColors.FreshNewsDarkThemeColors().bottomBar
                false -> FreshNewsColors.FreshNewsLightThemeColors().bottomBar
            },
            darkIcons = !darkTheme
        )
    }
}
