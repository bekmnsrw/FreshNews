package kfu.itis.freshnews.android.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.utils.shimmerEffect

@Composable
fun LatestNewsLoadingView() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(2) {
            Box(
                modifier = Modifier
                    .height(220.dp)
                    .width(320.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect(),
            )
        }
    }
}

fun LazyListScope.NewsOfCategoryLoadingView() {
    items(5) {
        Box(
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .shimmerEffect(),
        )
        Spacer(Modifier.height(8.dp))
    }
}
