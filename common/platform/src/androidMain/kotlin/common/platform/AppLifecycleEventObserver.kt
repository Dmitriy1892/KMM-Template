package common.platform

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppLifecycleEventObserver : LifecycleEventObserver {

    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this@AppLifecycleEventObserver)
    }

    val _lifecycleStateFlow = MutableStateFlow(Lifecycle.Event.ON_CREATE)
    val lifecycleStateFlow: StateFlow<Lifecycle.Event>
        get() = _lifecycleStateFlow.asStateFlow()

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        _lifecycleStateFlow.value = event
    }
}