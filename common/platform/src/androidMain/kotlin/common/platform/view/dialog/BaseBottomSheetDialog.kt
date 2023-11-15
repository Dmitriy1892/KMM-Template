package common.platform.view.dialog

import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog(
    @LayoutRes layoutRes: Int
) : BottomSheetDialogFragment(layoutRes) {

    protected abstract val binding: ViewBinding

    protected val TAG = this::class.java.simpleName
}