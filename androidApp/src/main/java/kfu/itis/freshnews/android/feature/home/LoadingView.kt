package kfu.itis.freshnews.android.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
                    .height(240.dp)
                    .width(320.dp)
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
                .height(240.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .shimmerEffect(),
        )
        Spacer(
            modifier = Modifier.height(8.dp),
        )
    }
}

@Composable
fun SearchedNewsLoadingView() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(10) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 12.dp,
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect(),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .width(if (index % 2 == 0) 128.dp else 256.dp)
                    .height(30.dp)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 12.dp,
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect(),
            )
            HorizontalDivider()
        }
    }
}
