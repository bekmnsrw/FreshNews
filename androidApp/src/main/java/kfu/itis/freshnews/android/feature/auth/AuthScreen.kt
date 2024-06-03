package kfu.itis.freshnews.android.feature.auth

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kfu.itis.freshnews.android.R
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes
import kfu.itis.freshnews.android.designsystem.theme.FreshNewsTheme
import kfu.itis.freshnews.android.utils.rememberEvent
import kfu.itis.freshnews.feature.auth.presentation.AuthAction
import kfu.itis.freshnews.feature.auth.presentation.AuthEvent
import kfu.itis.freshnews.feature.auth.presentation.AuthState
import kfu.itis.freshnews.feature.auth.presentation.AuthViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = koinViewModel(),
    navController: NavController,
    snackbarHostState: SnackbarHostState,
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
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun AuthActions(
    action: AuthAction?,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    val coroutineScope = rememberCoroutineScope()
    val errorMessage = stringResource(R.string.error_message)

    LaunchedEffect(action) {
        when (action) {
            null -> Unit

            AuthAction.NavigateHome -> {
                navController.navigate(FreshNewsRoutes.HOME_GRAPH_ROUTE) {
                    popUpTo(FreshNewsRoutes.AUTH_GRAPH_ROUTE) { inclusive = true }
                    popUpTo(FreshNewsRoutes.HOME_GRAPH_ROUTE) { inclusive = true }
                }
            }

            AuthAction.ShowError -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = errorMessage,
                        withDismissAction = true,
                    )
                }
            }
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
