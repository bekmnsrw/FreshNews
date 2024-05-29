package kfu.itis.freshnews.android.feature.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kfu.itis.freshnews.android.theme.FreshNewsTheme
import kfu.itis.freshnews.android.utils.rememberEvent
import kfu.itis.freshnews.feature.details.presentation.DetailsAction
import kfu.itis.freshnews.feature.details.presentation.DetailsEvent
import kfu.itis.freshnews.feature.details.presentation.DetailsState
import kfu.itis.freshnews.feature.details.presentation.DetailsViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = viewModel(),
) {
    val state by viewModel.states.collectAsStateWithLifecycle(initialValue = DetailsState())
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val eventHandler = rememberEvent<DetailsEvent> { detailsEvent -> viewModel.handleEvent(detailsEvent) }

    LaunchedEffect(Unit) {
        eventHandler(DetailsEvent.OnInit("Eating more fruits and vegetables"))
    }

    DetailsView(
        state = state,
        eventHandler = eventHandler,
    )

    DetailsActions(
        action = action,
    )
}

@Composable
private fun DetailsActions(action: DetailsAction?) {
    LaunchedEffect(action) {
        when (action) {
            /**
             * TODO: Implement
             */
            null -> Unit
            DetailsAction.NavigateBack -> Unit
            is DetailsAction.ShowError -> Unit
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview() {
    FreshNewsTheme {
        DetailsScreen()
    }
}
