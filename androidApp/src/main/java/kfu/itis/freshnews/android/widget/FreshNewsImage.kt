package kfu.itis.freshnews.android.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import kfu.itis.freshnews.android.R

@Composable
fun FreshNewsImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    AsyncImage(
        modifier = modifier,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .crossfade(true)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build(),
    )
}
