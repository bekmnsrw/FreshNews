package kfu.itis.freshnews.android.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kfu.itis.freshnews.feature.home.presentation.HomeEvent
import kfu.itis.freshnews.feature.home.presentation.HomeState

@Composable
fun HomeContent(
    state: HomeState,
    eventHandler: (HomeEvent) -> Unit,
) {
    Column {
        Button(onClick = { eventHandler(HomeEvent.OnArticleClick("")) }) {
            Text(text = "Click Me")
        }
    }
}
