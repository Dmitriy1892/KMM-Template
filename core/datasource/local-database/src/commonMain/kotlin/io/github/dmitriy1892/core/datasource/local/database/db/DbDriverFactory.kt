package io.github.dmitriy1892.core.datasource.local.database.db

import com.squareup.sqldelight.db.SqlDriver
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration

expect class DbDriverFactory constructor(platformConfiguration: PlatformConfiguration) {

    fun createDriver(schema: SqlDriver.Schema, name: String): SqlDriver
}