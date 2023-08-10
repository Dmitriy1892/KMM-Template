package io.github.dmitriy1892.common.platform

import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog(
    @LayoutRes layoutRes: Int
) : BottomSheetDialogFragment(layoutRes) {

    protected abstract val binding: ViewBinding

    private val TAG = this::class.java.simpleName
}