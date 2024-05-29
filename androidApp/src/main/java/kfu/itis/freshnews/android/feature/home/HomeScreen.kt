package kfu.itis.freshnews.android.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kfu.itis.freshnews.android.theme.FreshNewsTheme
import kfu.itis.freshnews.android.utils.rememberEvent
import kfu.itis.freshnews.feature.home.presentation.HomeAction
import kfu.itis.freshnews.feature.home.presentation.HomeEvent
import kfu.itis.freshnews.feature.home.presentation.HomeState
import kfu.itis.freshnews.feature.home.presentation.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
) {
    val state by viewModel.states.collectAsStateWithLifecycle(initialValue = HomeState())
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val eventHandler = rememberEvent<HomeEvent> { homeEvent -> viewModel.handleEvent(homeEvent) }

    LaunchedEffect(Unit) {
        eventHandler(HomeEvent.OnInit)
    }

    HomeView(
        state = state,
        eventHandler = eventHandler,
    )

    HomeActions(
        action = action,
    )
}

@Composable
private fun HomeActions(action: HomeAction?) {
    LaunchedEffect(action) {
        when (action) {
            /**
             * TODO: Implement
             */
            is HomeAction.NavigateDetails -> Unit
            is HomeAction.ShowError -> Unit
            null -> Unit
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    FreshNewsTheme {
        HomeScreen()
    }
}
