package kfu.itis.freshnews.android.feature.details

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
import kfu.itis.freshnews.android.utils.rememberEvent
import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.details.presentation.DetailsAction
import kfu.itis.freshnews.feature.details.presentation.DetailsEvent
import kfu.itis.freshnews.feature.details.presentation.DetailsState
import kfu.itis.freshnews.feature.details.presentation.DetailsViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = viewModel(),
    navController: NavController,
    articleDetails: ArticleDetails? = null,
    favoriteArticleId: Long? = null,
    snackbarHostState: SnackbarHostState,
) {
    val state by viewModel.states.collectAsStateWithLifecycle(initialValue = DetailsState())
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val eventHandler = rememberEvent<DetailsEvent> { detailsEvent -> viewModel.handleEvent(detailsEvent) }

    LaunchedEffect(Unit) {
        eventHandler(
            DetailsEvent.OnInit(
                articleDetails = articleDetails,
                favoriteArticleId = favoriteArticleId,
            )
        )
    }

    DetailsView(
        state = state,
        eventHandler = eventHandler,
    )

    DetailsActions(
        action = action,
        navController = navController,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun DetailsActions(
    action: DetailsAction?,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    val coroutineScope = rememberCoroutineScope()
    val errorMessage = stringResource(R.string.error_message)

    LaunchedEffect(action) {
        when (action) {
            null -> Unit

            DetailsAction.NavigateBack -> {
                navController.navigateUp()
            }

            DetailsAction.ShowError -> {
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
private fun DetailsScreenPreview() {
    FreshNewsTheme {
        DetailsView(
            state = DetailsState(),
            eventHandler = {},
        )
    }
}
