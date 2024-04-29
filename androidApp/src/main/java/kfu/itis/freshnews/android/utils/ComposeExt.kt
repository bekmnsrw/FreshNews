package kfu.itis.freshnews.android.utils

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * @sample `val onViewClick = rememberClick { showView = false }`
 */
@Composable
inline fun rememberEvent(crossinline block: @DisallowComposableCalls () -> Unit): () -> Unit = remember {
    {
        block()
    }
}

/**
 * @sample `val onViewClick = rememberClick { showView = it }`
 */
@Composable
inline fun <T> rememberEvent(crossinline block: @DisallowComposableCalls (T) -> Unit): (T) -> Unit = remember {
    { t ->
        block(t)
    }
}

fun LazyListScope.LazyColumnSpacer(height: Dp) {
    item {
        androidx.compose.foundation.layout.Spacer(
            modifier = Modifier.height(height),
        )
    }
}
