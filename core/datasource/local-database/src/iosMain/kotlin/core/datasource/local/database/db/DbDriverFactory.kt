package core.datasource.local.database.db

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration

actual class DbDriverFactory actual constructor(
    private val platformConfiguration: PlatformConfiguration
) {

    actual fun createDriver(
        schema: SqlSchema<QueryResult.Value<Unit>>,
        name: String
    ): SqlDriver = NativeSqliteDriver(schema, name)
}