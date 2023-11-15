package common.platform.view.viewbinding

import android.view.LayoutInflater
import android.view.View
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

inline fun <reified T: ViewBinding> Fragment.viewBinding(): FragmentViewBindingDelegate<T> {
    return FragmentViewBindingDelegate {
        val method = T::class.java.getMethod("bind", View::class.java)
        val binding = method.invoke(null, requireView())
        binding as T
    }
}

inline fun <reified T: ViewBinding> ComponentActivity.viewBinding(): ActivityViewBindingDelegate<T> {
    return ActivityViewBindingDelegate {
        val method = T::class.java.getMethod("inflate", LayoutInflater::class.java)
        val binding = method.invoke(null, layoutInflater)
        binding as T
    }
}