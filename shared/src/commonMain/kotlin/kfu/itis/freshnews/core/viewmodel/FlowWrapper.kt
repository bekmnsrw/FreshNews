package kfu.itis.freshnews.core.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

/**
 * Source:
 *   - [Habr Article](https://habr.com/ru/articles/596497/)
 *   - [GitHub](https://github.com/anioutkazharkova/kn_network_sample/blob/main/classic/shared/src/commonMain/kotlin/com/azharkova/kn_network_sample/Flow.kt)
 */
class FlowWrapper<T>(source: Flow<T>): Flow<T> by source {

    fun collect(
        onEach: (T) -> Unit,
        onCompletion: (cause: Throwable?) -> Unit,
    ): Cancellable {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        scope.launch {
            try {
                collect { data -> onEach(data) }
                onCompletion(null)
            } catch (throwable: Throwable) {
                onCompletion(throwable)
            }
        }

        return object : Cancellable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }
}

fun <T> Flow<T>.collect(
    onEach: (T) -> Unit,
    onCompletion: (cause: Throwable?) -> Unit,
): Cancellable {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    scope.launch {
        try {
            collect { data -> onEach(data) }
            onCompletion(null)
        } catch (throwable: Throwable) {
            onCompletion(throwable)
        }
    }

    return object : Cancellable {
        override fun cancel() {
            scope.cancel()
        }
    }
}

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
fun <T> Flow<T>.asFlowWrapper(): FlowWrapper<T> = FlowWrapper(this)

interface Cancellable {

    fun cancel()
}
