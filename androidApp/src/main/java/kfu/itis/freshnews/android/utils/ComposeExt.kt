package kfu.itis.freshnews.android.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.remember

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
