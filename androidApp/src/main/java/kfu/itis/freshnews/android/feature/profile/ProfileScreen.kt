package kfu.itis.freshnews.android.feature.profile

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
import kfu.itis.freshnews.feature.profile.presentation.ProfileAction
import kfu.itis.freshnews.feature.profile.presentation.ProfileEvent
import kfu.itis.freshnews.feature.profile.presentation.ProfileState
import kfu.itis.freshnews.feature.profile.presentation.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    navController: NavController,
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
    )
}

@Composable
private fun ProfileActions(
    action: ProfileAction?,
    navController: NavController,
) {
    LaunchedEffect(action) {
        when (action) {
            null -> Unit
            ProfileAction.NavigateAuth -> navController.navigate(FreshNewsRoutes.AUTH_GRAPH_ROUTE)
            is ProfileAction.ShowError -> Unit
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
