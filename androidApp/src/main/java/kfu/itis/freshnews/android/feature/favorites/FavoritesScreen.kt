package kfu.itis.freshnews.android.feature.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes
import kfu.itis.freshnews.android.navigation.NestedScreen
import kfu.itis.freshnews.android.theme.FreshNewsTheme
import kfu.itis.freshnews.android.utils.rememberEvent
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesAction
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesEvent
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesState
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesViewModel

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = viewModel(),
    navController: NavController,
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
    )
}

@Composable
private fun FavoritesActions(
    action: FavoritesAction?,
    navController: NavController,
) {
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
            is FavoritesAction.ShowError -> Unit
            FavoritesAction.NavigateAuth -> navController.navigate(FreshNewsRoutes.AUTH_GRAPH_ROUTE)
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
