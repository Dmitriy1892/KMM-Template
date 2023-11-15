package common.platform.view.viewbinding

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityViewBindingDelegate<T: ViewBinding>(
    private val bindingInitializer: (LayoutInflater) -> T
) : ViewBindingObserver<T>(), ReadOnlyProperty<Activity, T> {

    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return binding ?: kotlin.run {
            lifecycle?.let {
                it.addObserver(this)
                lifecycle = it
            }

            bindingInitializer(thisRef.layoutInflater).also { binding = it }
        }
    }
}