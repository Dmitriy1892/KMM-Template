plugins {
    id("multiplatform-library-convention")
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:data"))

                implementation(libs.bundles.common.local)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.sqlDelight.driver.android)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.sqlDelight.driver.ios)
            }
        }
    }
}

android {
    namespace = "io.github.dmitriy1892.core.datasource.local.database"
}

sqldelight {
    database("Database") {
        packageName = "io.github.dmitriy1892.core.datasource.local.database"
        sourceFolders = listOf("sqldelight")
    }
}