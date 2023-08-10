import extensions.iosRegularFramework

plugins {
    id("multiplatform-library-convention")
}

kotlin {
    iosRegularFramework {
        baseName = "SharedSDK"
        transitiveExport = false
        isStatic = true
        linkerOpts.add("-lsqlite3")

        export(project(":di:api"))
        export(project(":core:domain"))

        export(libs.kmm.utils)
        export(libs.kmm.mvvm.core)
    }
    
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":di:api"))

                implementation(project(":core:domain"))
                implementation(project(":core:data"))
                implementation(project(":core:datasource:local-database"))
                implementation(project(":core:datasource:network"))

                implementation(project(":navigation:api"))
                implementation(project(":navigation:impl"))

                implementation(libs.koin.core)

                implementation(libs.kmm.utils)
                implementation(libs.kmm.mvvm.core)
                implementation(libs.kmm.mvvm.koin)

                implementation(libs.kotlinx.coroutines.core)

                implementation(libs.ktor.core)
            }
        }

        androidMain {
            dependencies {
                api(libs.kmm.utils)
                api(libs.kmm.mvvm.core)
            }
        }

        iosMain {
            dependencies {
                api(project(":di:api"))
                api(project(":core:domain"))

                api(libs.kmm.utils)
                api(libs.kmm.mvvm.core)
            }
        }
    }
}

android {
    namespace = "io.github.dmitriy1892.di.impl"
}