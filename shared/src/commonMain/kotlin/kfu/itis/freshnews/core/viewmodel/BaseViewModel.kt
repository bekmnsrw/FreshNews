package kfu.itis.freshnews.core.viewmodel

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : Any, Action, Event>(
    initialState: State,
) : KmpViewModel() {

    private val _state = MutableStateFlow(initialState)

    protected var state: State
        get() = _state.value
        set(value) { _state.value = value }

    private val _action = MutableSharedFlow<Action>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    protected var action: Action?
        get() = if (_action.replayCache.isNotEmpty()) _action.replayCache.last() else null
        set(value) { scope.launch { if (value != null) _action.emit(value) else _action.resetReplayCache() } }

    val states: FlowWrapper<State>
        get() = _state.asStateFlow().asFlowWrapper()

    val actions: FlowWrapper<Action>
        get() = _action.asSharedFlow().asFlowWrapper()

    abstract fun handleEvent(event: Event)
}
