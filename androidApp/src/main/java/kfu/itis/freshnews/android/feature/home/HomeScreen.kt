package kfu.itis.freshnews.android.feature.home

import android.content.Intent
import android.provider.Settings
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
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
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    val state by viewModel.states.collectAsStateWithLifecycle(initialValue = HomeState())
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val eventHandler = rememberEvent<HomeEvent> { homeEvent -> viewModel.handleEvent(homeEvent) }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        if (state.latestArticles.isEmpty()) {
            eventHandler(HomeEvent.ReloadNews)
        }
    }

    HomeView(
        state = state,
        eventHandler = eventHandler,
    )

    HomeActions(
        action = action,
        navController = navController,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun HomeActions(
    action: HomeAction?,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

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

            HomeAction.ShowError -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Oops, something went wrong :(",
                        withDismissAction = true,
                    )
                }
            }

            HomeAction.OpenWiFiSettings -> {
                context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
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
