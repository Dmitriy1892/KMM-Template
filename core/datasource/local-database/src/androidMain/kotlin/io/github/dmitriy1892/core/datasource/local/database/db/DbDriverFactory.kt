package io.github.dmitriy1892.core.datasource.local.database.db

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration

actual class DbDriverFactory actual constructor(
    private val platformConfiguration: PlatformConfiguration
) {

    actual fun createDriver(schema: SqlDriver.Schema, name: String): SqlDriver =
        AndroidSqliteDriver(schema, platformConfiguration.androidContext, name)
}