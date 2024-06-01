package kfu.itis.freshnews.android.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes
import kfu.itis.freshnews.android.designsystem.theme.FreshNewsTheme
import kfu.itis.freshnews.android.utils.rememberEvent
import kfu.itis.freshnews.feature.splash.presentation.SplashAction
import kfu.itis.freshnews.feature.splash.presentation.SplashEvent
import kfu.itis.freshnews.feature.splash.presentation.SplashState
import kfu.itis.freshnews.feature.splash.presentation.SplashViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = viewModel(),
    navController: NavController,
) {
    val state by viewModel.states.collectAsStateWithLifecycle(initialValue = SplashState())
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val eventHandler = rememberEvent<SplashEvent> { splashEvent -> viewModel.handleEvent(splashEvent) }

    SplashView(
        state = state,
        eventHandler = eventHandler,
    )

    SplashActions(
        action = action,
        navController = navController,
    )
}

@Composable
private fun SplashActions(
    action: SplashAction?,
    navController: NavController,
) {
    LaunchedEffect(action) {
        when (action) {
            null -> Unit
            SplashAction.NavigateAuth -> navController.navigate(FreshNewsRoutes.AUTH_GRAPH_ROUTE) {
                popUpTo(FreshNewsRoutes.SPLASH_GRAPH_ROUTE) { inclusive = true }
            }
            SplashAction.NavigateHome -> navController.navigate(FreshNewsRoutes.HOME_GRAPH_ROUTE) {
                popUpTo(FreshNewsRoutes.SPLASH_GRAPH_ROUTE) { inclusive = true }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    FreshNewsTheme {
        SplashView(
            state = SplashState(),
            eventHandler = {},
        )
    }
}
