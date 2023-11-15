import extensions.androidMainDependencies
import extensions.commonMainDependencies
import extensions.iosMainDependencies

plugins {
    id("kmm.library.project")
    alias(libs.plugins.sqlDelight)
}

android {
    namespace = "core.datasource.local.database"
}

commonMainDependencies {
    implementation(project(":core:data"))

    implementation(libs.sqlDelight.core)
    implementation(libs.sqlDelight.extensions)
}

androidMainDependencies {
    implementation(libs.sqlDelight.driver.android)
}

iosMainDependencies {
    implementation(libs.sqlDelight.driver.ios)
}

sqldelight {
    databases {
        linkSqlite.set(false)
        create("Database") {
            packageName.set("core.datasource.local.database.generated")
        }
    }
}