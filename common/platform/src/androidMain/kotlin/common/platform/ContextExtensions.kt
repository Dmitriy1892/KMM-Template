package common.platform

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings

fun Context.openFileByAnotherApp(uri: Uri, mime: String) {
    val openFileIntent = Intent(Intent.ACTION_VIEW)
    openFileIntent.setDataAndType(uri, mime)
    openFileIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
    startActivity(openFileIntent)
}

fun Context.restartApplication() {
    val packageManager: PackageManager = this.packageManager
    val intent = packageManager.getLaunchIntentForPackage(this.packageName)
    val componentName: ComponentName = requireNotNull(intent?.component)
    val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
    this.startActivity(restartIntent)
    Runtime.getRuntime().exit(0)
}

@SuppressLint("HardwareIds")
fun Context.getAndroidDeviceId(): String =
    Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
