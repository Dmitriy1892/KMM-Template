plugins {
    id("multiplatform-library-convention")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:data"))

                implementation(libs.bundles.common.network)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.ktor.android)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktor.ios)
                implementation(libs.ktor.darwin)
            }
        }
    }
}

android {
    namespace = "io.github.dmitriy1892.core.datasource.network"
}