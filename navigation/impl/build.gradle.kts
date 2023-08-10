plugins {
    id("multiplatform-library-convention")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":navigation:api"))
            }
        }
    }
}

android {
    namespace = "io.github.dmitriy1892.navigation.impl"
}