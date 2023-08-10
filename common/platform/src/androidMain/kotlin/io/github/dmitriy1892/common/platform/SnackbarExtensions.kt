package io.github.dmitriy1892.common.platform

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.activity.ComponentActivity
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar.Duration
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSimpleSnackbar(
    @StringRes messageRes: Int,
    @Duration duration: Int = Snackbar.LENGTH_SHORT
): Snackbar {
    val snackbar = Snackbar.make(requireView(), messageRes, duration)
    snackbar.show()
    return snackbar
}

fun ComponentActivity.showSimpleSnackbar(
    @StringRes messageRes: Int,
    @Duration duration: Int = Snackbar.LENGTH_SHORT
): Snackbar {
    val snackbar = Snackbar.make(window.decorView.rootView, messageRes, duration)
    snackbar.show()
    return snackbar
}

fun Fragment.showSimpleSnackbar(
    message: String,
    @Duration duration: Int = Snackbar.LENGTH_SHORT
): Snackbar {
    val snackbar = Snackbar.make(requireView(), message, duration)
    snackbar.show()
    return snackbar
}

fun ComponentActivity.showSimpleSnackbar(
    message: String,
    @Duration duration: Int = Snackbar.LENGTH_SHORT
): Snackbar {
    val snackbar = Snackbar.make(window.decorView.rootView, message, duration)
    snackbar.show()
    return snackbar
}

@SuppressLint("InflateParams")
fun Fragment.showCustomSnackbar(
    @DimenRes topMargin: Int,
    @DimenRes bottomMargin: Int,
    @DimenRes startMargin: Int,
    @DimenRes endMargin: Int,
    @LayoutRes layoutRes: Int,
    @Duration duration: Int = Snackbar.LENGTH_SHORT,
    onSnackViewCreated: (layout: View) -> Unit
): Snackbar {
    val snackbar = Snackbar.make(requireView(), "", duration).apply {
        animationMode = Snackbar.ANIMATION_MODE_SLIDE
        view.setBackgroundColor(Color.TRANSPARENT)
    }

    val customLayout = layoutInflater.inflate(layoutRes, null)
    onSnackViewCreated(customLayout)

    val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

    val topMarginPx = resources.getDimensionPixelSize(topMargin)
    val bottomMarginPx = resources.getDimensionPixelSize(bottomMargin)
    val startMarginPx = resources.getDimensionPixelSize(startMargin)
    val endMarginPx = resources.getDimensionPixelSize(endMargin)

    snackbarLayout.apply {
        setPadding(startMarginPx, topMarginPx, endMarginPx, bottomMarginPx)
        addView(customLayout, 0)
    }

    snackbar.show()

    return snackbar
}

fun ComponentActivity.showCustomSnackbar(
    @DimenRes topMargin: Int,
    @DimenRes bottomMargin: Int,
    @DimenRes startMargin: Int,
    @DimenRes endMargin: Int,
    @LayoutRes layoutRes: Int,
    @Duration duration: Int = Snackbar.LENGTH_SHORT,
    onSnackViewCreated: (layout: View) -> Unit
): Snackbar {
    val snackbar = Snackbar.make(window.decorView.rootView, "", duration).apply {
        animationMode = Snackbar.ANIMATION_MODE_SLIDE
        view.setBackgroundColor(Color.TRANSPARENT)
    }

    val customLayout = layoutInflater.inflate(layoutRes, null)
    onSnackViewCreated(customLayout)

    val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

    val topMarginPx = resources.getDimensionPixelSize(topMargin)
    val bottomMarginPx = resources.getDimensionPixelSize(bottomMargin)
    val startMarginPx = resources.getDimensionPixelSize(startMargin)
    val endMarginPx = resources.getDimensionPixelSize(endMargin)

    snackbarLayout.apply {
        setPadding(startMarginPx, topMarginPx, endMarginPx, bottomMarginPx)
        addView(customLayout, 0)
    }

    snackbar.show()

    return snackbar
}