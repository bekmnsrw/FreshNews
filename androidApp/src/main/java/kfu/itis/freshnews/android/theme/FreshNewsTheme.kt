package kfu.itis.freshnews.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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
                true -> FreshNewsColors.FreshNewsDarkThemeColors().primary
                false -> FreshNewsColors.FreshNewsLightThemeColors().primary
            },
            darkIcons = !darkTheme
        )
        systemUiController.setNavigationBarColor(
            color = when (darkTheme) {
                true -> FreshNewsColors.FreshNewsDarkThemeColors().bottomBarContainer
                false -> FreshNewsColors.FreshNewsLightThemeColors().bottomBarContainer
            },
            darkIcons = !darkTheme
        )
    }
}
