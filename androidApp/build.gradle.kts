plugins {
    id("android-application-convention")
    id("android-base-compose-convention")
}

kotlin {
    sourceSets {
        androidMain {
            dependencies {
                implementation(project(":core:datasource:network"))
                implementation(project(":core:datasource:local-database"))
                implementation(project(":resources:translations"))
                implementation(libs.bundles.android.feature.compose)
                implementation(libs.kmm.utils)
            }
        }
    }
}

android {
    namespace = "io.github.dmitriy1892.app"

    defaultConfig {
        applicationId = "io.github.dmitriy1892.app"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}