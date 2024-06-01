package kfu.itis.freshnews.android.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kfu.itis.freshnews.android.navigation.NestedScreen
import kfu.itis.freshnews.android.designsystem.theme.FreshNewsTheme
import kfu.itis.freshnews.android.utils.rememberEvent
import kfu.itis.freshnews.feature.home.presentation.HomeAction
import kfu.itis.freshnews.feature.home.presentation.HomeEvent
import kfu.itis.freshnews.feature.home.presentation.HomeState
import kfu.itis.freshnews.feature.home.presentation.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController,
) {
    val state by viewModel.states.collectAsStateWithLifecycle(initialValue = HomeState())
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val eventHandler = rememberEvent<HomeEvent> { homeEvent -> viewModel.handleEvent(homeEvent) }

    HomeView(
        state = state,
        eventHandler = eventHandler,
    )

    HomeActions(
        action = action,
        navController = navController,
    )
}

@Composable
private fun HomeActions(
    action: HomeAction?,
    navController: NavController,
) {
    LaunchedEffect(action) {
        when (action) {
            null -> Unit
            is HomeAction.NavigateDetails -> {
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set(
                        key = NestedScreen.DetailsScreenFromHome.argKey,
                        value = action.article,
                    )
                navController.navigate(NestedScreen.DetailsScreenFromHome.route)
            }
            is HomeAction.ShowError -> Unit
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    FreshNewsTheme {
        HomeView(
            state = HomeState(),
            eventHandler = {},
        )
    }
}
