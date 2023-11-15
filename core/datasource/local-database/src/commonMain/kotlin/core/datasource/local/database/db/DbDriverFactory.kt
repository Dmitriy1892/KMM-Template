package core.datasource.local.database.db

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import org.koin.core.annotation.Single

@Single
expect class DbDriverFactory(platformConfiguration: PlatformConfiguration) {

    fun createDriver(
        schema: SqlSchema<QueryResult.Value<Unit>>,
        name: String
    ): SqlDriver
}