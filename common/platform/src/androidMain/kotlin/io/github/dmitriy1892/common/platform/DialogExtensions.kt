package io.github.dmitriy1892.common.platform

import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showDialog(
    @StringRes titleRes: Int,
    @StringRes messageRes: Int,
    message: String? = null,
    @StringRes positiveButtonTextRes: Int,
    positiveButtonClickListener: () -> Unit,
    @StringRes negativeButtonTextRes: Int? = null,
    negativeButtonClickListener: (() -> Unit)? = null,
    @DimenRes horizontalInset: Int,
    @DrawableRes backgroundRes: Int,
    isCancellable: Boolean,
    @StyleRes themeRes: Int
): AlertDialog {
    val builder = MaterialAlertDialogBuilder(requireContext(), themeRes)
        .setTitle(titleRes)
        .setMessage(messageRes)
        .setBackgroundInsetStart(resources.getDimensionPixelSize(horizontalInset))
        .setBackgroundInsetEnd(resources.getDimensionPixelSize(horizontalInset))
        .setBackground(ContextCompat.getDrawable(requireContext(), backgroundRes))
        .setPositiveButton(positiveButtonTextRes) { _, _ -> positiveButtonClickListener() }
        .setCancelable(isCancellable)

    if (message != null) builder.setMessage(message) else builder.setMessage(messageRes)

    if (negativeButtonTextRes != null) {
        builder.setNegativeButton(negativeButtonTextRes) { _, _ ->
            negativeButtonClickListener?.invoke()
        }
    }

    return builder.show()
}

fun ComponentActivity.showDialog(
    @StringRes titleRes: Int,
    @StringRes messageRes: Int,
    message: String? = null,
    @StringRes positiveButtonTextRes: Int,
    positiveButtonClickListener: () -> Unit,
    @StringRes negativeButtonTextRes: Int? = null,
    negativeButtonClickListener: (() -> Unit)? = null,
    @DimenRes horizontalInset: Int,
    @DrawableRes backgroundRes: Int,
    isCancellable: Boolean,
    @StyleRes themeRes: Int
) {
    val builder = MaterialAlertDialogBuilder(this, themeRes)
        .setTitle(titleRes)
        .setMessage(messageRes)
        .setBackgroundInsetStart(resources.getDimensionPixelSize(horizontalInset))
        .setBackgroundInsetEnd(resources.getDimensionPixelSize(horizontalInset))
        .setBackground(ContextCompat.getDrawable(this, backgroundRes))
        .setPositiveButton(positiveButtonTextRes) { _, _ -> positiveButtonClickListener() }
        .setCancelable(isCancellable)

    if (message != null) builder.setMessage(message) else builder.setMessage(messageRes)

    if (negativeButtonTextRes != null) {
        builder.setNegativeButton(negativeButtonTextRes) { _, _ ->
            negativeButtonClickListener?.invoke()
        }
    }

    builder.show()
}

fun Fragment.showLockUiProgressDialog(@LayoutRes progressLayoutRes: Int): AlertDialog {
    return MaterialAlertDialogBuilder(requireContext(), R.style.UIKit_LockProgressBarDialogTheme)
        .setView(progressLayoutRes)
        .setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_progress_dialog))
        .setCancelable(false)
        .show()
        .apply {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
}

fun ComponentActivity.showLockUiProgressDialog(@LayoutRes progressLayoutRes: Int): AlertDialog {
    return MaterialAlertDialogBuilder(this, R.style.UIKit_LockProgressBarDialogTheme)
        .setView(progressLayoutRes)
        .setBackground(ContextCompat.getDrawable(this, R.drawable.bg_progress_dialog))
        .setCancelable(false)
        .show()
}