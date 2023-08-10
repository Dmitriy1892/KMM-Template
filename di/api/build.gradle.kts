plugins {
    id("multiplatform-library-convention")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)
            }
        }
    }
}

android {
    namespace = "io.github.dmitriy1892.di.api"
}