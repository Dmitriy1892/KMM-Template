plugins {
    id("multiplatform-library-convention")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
            }
        }
    }
}

android {
    namespace = "io.github.dmitriy1892.core.domain"
}