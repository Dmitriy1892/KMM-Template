import extensions.iosRegularFramework

plugins {
    id("multiplatform-library-convention")
    alias(libs.plugins.moko.resources)
}

kotlin {
    iosRegularFramework {
        isStatic = true
        baseName = "CoreTranslations"
    }

    sourceSets {
        commonMain {
            dependencies {
                api(libs.moko.resources)
                api(libs.moko.resources.compose)
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "io.github.dmitriy1892.resources.translations"
}

android {
    namespace = "io.github.dmitriy1892.resources.translations"
}