package io.github.dmitriy1892.core.datasource.local.database.db

import io.github.dmitriy1892.core.datasource.local.database.Database
import io.github.dmitriy1892.kmm.utils.coroutines.withThreadLock
import kotlinx.coroutines.sync.Mutex
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object DbCreator {

    private var INSTANCE: Database? = null
    private val mutex = Mutex()

    fun getDatabase(driverFactory: DbDriverFactory): Database = mutex.withThreadLock {
        INSTANCE ?: run {
            val driver = driverFactory.createDriver(Database.Schema, "database.db")
            INSTANCE = Database(driver)

            INSTANCE!!
        }
    }
}