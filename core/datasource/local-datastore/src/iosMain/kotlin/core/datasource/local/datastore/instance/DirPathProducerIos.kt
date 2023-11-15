package core.datasource.local.datastore.instance

import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual fun produceDirPath(
    fileName: String,
    platformConfiguration: PlatformConfiguration
): Path {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )

    val dirPath = requireNotNull(documentDirectory?.path)

    return "$dirPath/$fileName".toPath()
}