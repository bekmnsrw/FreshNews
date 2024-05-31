package kfu.itis.freshnews.android.feature.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes
import kfu.itis.freshnews.android.theme.FreshNewsTheme
import kfu.itis.freshnews.android.utils.rememberEvent
import kfu.itis.freshnews.feature.auth.presentation.AuthAction
import kfu.itis.freshnews.feature.auth.presentation.AuthEvent
import kfu.itis.freshnews.feature.auth.presentation.AuthState
import kfu.itis.freshnews.feature.auth.presentation.AuthViewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = viewModel(),
    navController: NavController,
) {
    val state by viewModel.states.collectAsStateWithLifecycle(initialValue = AuthState())
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val eventHandler = rememberEvent<AuthEvent> { authEvent -> viewModel.handleEvent(authEvent) }

    AuthView(
        state = state,
        eventHandler = eventHandler,
    )

    AuthActions(
        action = action,
        navController = navController,
    )
}

@Composable
private fun AuthActions(
    action: AuthAction?,
    navController: NavController,
) {
    LaunchedEffect(action) {
        when (action) {
            null -> Unit
            AuthAction.NavigateHome -> navController.navigate(FreshNewsRoutes.HOME_GRAPH_ROUTE) {
                popUpTo(FreshNewsRoutes.AUTH_GRAPH_ROUTE) { inclusive = true }
                popUpTo(FreshNewsRoutes.HOME_GRAPH_ROUTE) { inclusive = true }
            }
            is AuthAction.ShowError -> Unit
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthScreenPreview() {
    FreshNewsTheme {
        AuthView(
            state = AuthState(),
            eventHandler = {},
        )
    }
}
