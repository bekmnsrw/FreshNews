package kfu.itis.freshnews.core.viewmodel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Action, Event>(
    initialState: State,
) : KmpViewModel() {

    private val _state = MutableStateFlow(initialState)

    protected var state: State
        get() = _state.value
        set(value) { _state.value = value }

    private val _action = Channel<Action>()

    protected var action: Action?
        get() = if (!_action.isEmpty) _action.tryReceive().getOrNull() else null
        set(value) { scope.launch { if (value != null) _action.send(value) else _action.cancel() } }

    val states: FlowWrapper<State>
        get() = _state.asStateFlow().asFlowWrapper()

    val actions: FlowWrapper<Action>
        get() = _action.receiveAsFlow().asFlowWrapper()

    abstract fun handleEvent(event: Event)
}
