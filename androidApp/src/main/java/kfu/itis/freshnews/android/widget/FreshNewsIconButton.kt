package kfu.itis.freshnews.android.widget

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FreshNewsIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    tint: Color,
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = icon,
            tint = tint,
            contentDescription = null,
        )
    }
}
