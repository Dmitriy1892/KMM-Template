plugins {
    id("multiplatform-library-convention")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":core:domain"))

                implementation(libs.kotlinx.coroutines.core)
            }
        }
    }
}

android {
    namespace = "io.github.dmitriy1892.core.data"
}