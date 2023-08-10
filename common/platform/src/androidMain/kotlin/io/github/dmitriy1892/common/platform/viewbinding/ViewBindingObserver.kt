package io.github.dmitriy1892.common.platform.viewbinding

import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding

open class ViewBindingObserver<T: ViewBinding> : DefaultLifecycleObserver {

    protected var binding: T? = null
    protected var lifecycle: Lifecycle? = null

    override fun onDestroy(owner: LifecycleOwner) {
        lifecycle?.removeObserver(this)
        lifecycle = null
        binding = null
    }
}