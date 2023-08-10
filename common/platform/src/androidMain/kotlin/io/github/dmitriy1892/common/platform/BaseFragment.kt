package io.github.dmitriy1892.common.platform

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {

    private val TAG = this::class.java.simpleName

    protected abstract val binding: ViewBinding

    private var progressDialog: AlertDialog? = null

    override fun onDestroyView() {
        super.onDestroyView()
        progressDialog?.dismiss()
        progressDialog = null
    }

    protected open fun createProgressDialog(): AlertDialog? = null

    protected fun showProgress() {
        if (progressDialog?.isShowing == true) return
        progressDialog = createProgressDialog()?.apply { if (!isShowing) show() }
    }

    protected fun hideProgress() {
        progressDialog?.dismiss()
        progressDialog = null
    }
}