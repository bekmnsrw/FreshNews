package kfu.itis.freshnews.android.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kfu.itis.freshnews.feature.details.presentation.DetailsEvent
import kfu.itis.freshnews.feature.details.presentation.DetailsState

@Composable
fun DetailsView(
    state: DetailsState,
    eventHandler: (DetailsEvent) -> Unit,
) {

    Column {
        Text(text = "Hello, Details")
    }
}
