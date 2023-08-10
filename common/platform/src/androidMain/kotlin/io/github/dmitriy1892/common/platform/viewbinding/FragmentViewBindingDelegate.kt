package io.github.dmitriy1892.common.platform.viewbinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T: ViewBinding>(
    private val viewBinder: (View) -> T
) : ViewBindingObserver<T>(), ReadOnlyProperty<Fragment, T> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return binding ?: kotlin.run {
            thisRef.viewLifecycleOwner.lifecycle.let {
                it.addObserver(this)
                lifecycle = it
            }

            viewBinder(thisRef.requireView()).also { binding = it }
        }
    }
}