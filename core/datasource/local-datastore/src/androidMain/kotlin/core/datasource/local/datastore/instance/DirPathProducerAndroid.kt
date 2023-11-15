package core.datasource.local.datastore.instance

import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import okio.Path
import okio.Path.Companion.toPath

actual fun produceDirPath(
    fileName: String,
    platformConfiguration: PlatformConfiguration
): Path = platformConfiguration.androidContext.filesDir.resolve(fileName).absolutePath.toPath()
