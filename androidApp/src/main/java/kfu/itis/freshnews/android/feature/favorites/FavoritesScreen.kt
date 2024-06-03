package kfu.itis.freshnews.android.feature.favorites

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
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes
import kfu.itis.freshnews.android.navigation.NestedScreen
import kfu.itis.freshnews.android.designsystem.theme.FreshNewsTheme
import kfu.itis.freshnews.android.utils.rememberEvent
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesAction
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesEvent
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesState
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = viewModel(),
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    val state by viewModel.states.collectAsStateWithLifecycle(initialValue = FavoritesState())
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val eventHandler = rememberEvent<FavoritesEvent> { favoritesEvent -> viewModel.handleEvent(favoritesEvent) }

    FavoritesView(
        state = state,
        eventHandler = eventHandler,
    )

    FavoritesActions(
        action = action,
        navController = navController,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun FavoritesActions(
    action: FavoritesAction?,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    val coroutineScope = rememberCoroutineScope()
    val errorMessage = stringResource(R.string.error_message)

    LaunchedEffect(action) {
        when (action) {
            null -> Unit

            is FavoritesAction.NavigateDetails -> {
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set(
                        key = NestedScreen.DetailsScreenFromFavorites.argKey,
                        value = action.id,
                    )
                navController.navigate(NestedScreen.DetailsScreenFromFavorites.route)
            }

            FavoritesAction.ShowError -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = errorMessage,
                        withDismissAction = true,
                    )
                }
            }

            FavoritesAction.NavigateAuth -> {
                navController.navigate(FreshNewsRoutes.AUTH_GRAPH_ROUTE)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesScreenPreview() {
    FreshNewsTheme {
        FavoritesView(
            state = FavoritesState(),
            eventHandler = {},
        )
    }
}
