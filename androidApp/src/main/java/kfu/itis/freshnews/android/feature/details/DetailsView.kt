package kfu.itis.freshnews.android.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kfu.itis.freshnews.feature.details.presentation.DetailsEvent
import kfu.itis.freshnews.feature.details.presentation.DetailsState

@Composable
fun DetailsView(
    state: DetailsState,
    eventHandler: (DetailsEvent) -> Unit,
    title: String,
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello, Details")
        Text(text = title)
    }
    Button(onClick = { eventHandler(DetailsEvent.OnArrowBackClick) }) {}
}
