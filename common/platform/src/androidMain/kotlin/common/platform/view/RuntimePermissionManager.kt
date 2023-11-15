package common.platform.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.MainThread
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

abstract class RuntimePermissionManager {

    private var _activity: ComponentActivity? = null
    protected val activity: ComponentActivity
        get(): ComponentActivity = _activity
            ?: _fragment?.activity
            ?: throw NullPointerException("Activity not found")

    private var _fragment: Fragment? = null
    protected val fragment: Fragment
        get(): Fragment = _fragment ?: throw NullPointerException("Fragment not found")

    protected val permission: String
    protected var permissionGrantedAction: () -> Unit = {}

    protected val permissionRequestLauncher: ActivityResultLauncher<String>

    @MainThread
    constructor(activity: ComponentActivity, permission: String) {
        _activity = activity
        this.permission = permission

        val lifecycleObserver = object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)

                _activity?.lifecycle?.removeObserver(this)
                _fragment?.lifecycle?.removeObserver(this)
                _activity = null
                _fragment = null
            }
        }
        this.activity.lifecycle.addObserver(lifecycleObserver)

        this.permissionRequestLauncher = registerPermissionCallback(activity)
    }

    @MainThread
    constructor(fragment: Fragment, permission: String) {
        this._fragment = fragment
        this.permission = permission

        val lifecycleObserver = object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)

                _activity?.lifecycle?.removeObserver(this)
                _fragment?.lifecycle?.removeObserver(this)
                _activity = null
                _fragment = null
            }
        }
        this.activity.lifecycle.addObserver(lifecycleObserver)
        this.fragment.lifecycle.addObserver(lifecycleObserver)

        this.permissionRequestLauncher = registerPermissionCallback(fragment)
    }

    @MainThread
    fun startPermissionChecking(permissionGrantedAction: () -> Unit = {}) {
        this.permissionGrantedAction = permissionGrantedAction

        if (checkPermission()) this.permissionGrantedAction()
        else _fragment?.let { showPermissionRationale(it) } ?: showPermissionRationale(activity)
    }

    protected abstract fun showPermissionRationale(activity: ComponentActivity)

    protected abstract fun showPermissionRationale(fragment: Fragment)

    protected abstract fun showPermissionDeniedInfo(activity: ComponentActivity)

    protected abstract fun showPermissionDeniedInfo(fragment: Fragment)

    protected open fun checkPermission(): Boolean =
        ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED

    protected open fun requestPermission() = permissionRequestLauncher.launch(permission)

    protected open fun shouldShowPermissionRationale(activity: ComponentActivity): Boolean =
        activity.shouldShowRequestPermissionRationale(permission)

    protected fun openSystemSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:" + activity.packageName)
        try {
            activity.startActivity(intent)
        } catch (_: ActivityNotFoundException) {
        }
    }

    private fun registerPermissionCallback(
        activity: ComponentActivity
    ): ActivityResultLauncher<String> = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        when {
            isGranted -> permissionGrantedAction()
            shouldShowPermissionRationale(activity) -> showPermissionRationale(activity)
            else -> showPermissionDeniedInfo(activity)
        }
    }

    private fun registerPermissionCallback(
        fragment: Fragment
    ): ActivityResultLauncher<String> = fragment.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        when {
            isGranted -> permissionGrantedAction()
            shouldShowPermissionRationale(activity) -> showPermissionRationale(fragment)
            else -> showPermissionDeniedInfo(fragment)
        }
    }
}