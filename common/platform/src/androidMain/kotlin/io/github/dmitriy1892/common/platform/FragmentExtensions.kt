package io.github.dmitriy1892.common.platform

import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.showSoftInputKeyboard() {
    ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
        ?.showSoftInput(requireActivity().window.currentFocus, InputMethodManager.SHOW_IMPLICIT)
}