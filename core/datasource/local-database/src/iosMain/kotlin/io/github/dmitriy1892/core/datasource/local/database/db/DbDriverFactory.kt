package io.github.dmitriy1892.core.datasource.local.database.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration

actual class DbDriverFactory actual constructor(
    private val platformConfiguration: PlatformConfiguration
) {

    actual fun createDriver(schema: SqlDriver.Schema, name: String): SqlDriver {
        return NativeSqliteDriver(schema, name)
    }
}