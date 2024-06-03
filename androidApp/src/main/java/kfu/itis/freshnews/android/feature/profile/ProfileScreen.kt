package kfu.itis.freshnews.android.feature.profile

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kfu.itis.freshnews.android.R
import kfu.itis.freshnews.android.designsystem.theme.FreshNewsTheme
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes
import kfu.itis.freshnews.android.utils.rememberEvent
import kfu.itis.freshnews.feature.profile.presentation.ProfileAction
import kfu.itis.freshnews.feature.profile.presentation.ProfileEvent
import kfu.itis.freshnews.feature.profile.presentation.ProfileState
import kfu.itis.freshnews.feature.profile.presentation.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    val state by viewModel.states.collectAsStateWithLifecycle(initialValue = ProfileState())
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val eventHandler = rememberEvent<ProfileEvent> { profileEvent -> viewModel.handleEvent(profileEvent) }

    ProfileView(
        state = state,
        eventHandler = eventHandler,
    )

    ProfileActions(
        action = action,
        navController = navController,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun ProfileActions(
    action: ProfileAction?,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    val coroutineScope = rememberCoroutineScope()
    val errorMessage = stringResource(R.string.error_message)

    LaunchedEffect(action) {
        when (action) {
            null -> Unit

            ProfileAction.NavigateAuth -> {
                navController.navigate(FreshNewsRoutes.AUTH_GRAPH_ROUTE)
            }

            ProfileAction.ShowError -> {
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
private fun ProfileScreenPreview() {
    FreshNewsTheme {
        ProfileView(
            state = ProfileState(),
            eventHandler = {},
        )
    }
}
