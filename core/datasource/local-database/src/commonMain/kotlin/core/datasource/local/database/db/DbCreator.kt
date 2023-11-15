package core.datasource.local.database.db

import core.datasource.local.database.generated.Database
import io.github.dmitriy1892.kmm.utils.coroutines.mutex.withThreadLock
import kotlinx.coroutines.sync.Mutex
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object DbCreator {

    private var INSTANCE: Database? = null
    private val mutex = Mutex()

    fun getDatabase(
        driverFactory: DbDriverFactory,
        databaseName: String = "database.db"
    ): Database = mutex.withThreadLock {
        INSTANCE ?: run {
            val driver = driverFactory.createDriver(Database.Schema, databaseName)
            INSTANCE = Database(driver)

            INSTANCE!!
        }
    }
}