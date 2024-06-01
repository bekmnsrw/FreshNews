package kfu.itis.freshnews.android.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.designsystem.icon.FreshNewsIcons
//import kfu.itis.freshnews.android.designsystem.theme.ThemeProvider
import kfu.itis.freshnews.feature.splash.presentation.SplashEvent
import kfu.itis.freshnews.feature.splash.presentation.SplashState

@Composable
fun SplashView(
    state: SplashState,
    eventHandler: (SplashEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            SplashContent(
                scaffoldPadding = paddingValues,
                state = state,
                eventHandler = eventHandler,
            )
        }
    )
}

@Composable
private fun SplashContent(
    scaffoldPadding: PaddingValues,
    state: SplashState,
    eventHandler: (SplashEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            imageVector = FreshNewsIcons.NEWSPAPER,
            contentDescription = null,
            modifier = Modifier.size(96.dp),
//            colorFilter = ColorFilter.tint(ThemeProvider.colors.accent),
        )
    }
}
