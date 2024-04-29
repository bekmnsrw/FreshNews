package kfu.itis.freshnews.android.widget

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FreshNewsIcon(
    icon: ImageVector,
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
    )
}
