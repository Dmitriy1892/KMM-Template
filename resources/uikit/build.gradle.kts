import extensions.iosRegularFramework

plugins {
    id("multiplatform-library-convention")
    alias(libs.plugins.moko.resources)
}

kotlin {
    iosRegularFramework {
        isStatic = true
        baseName = "CoreUiKit"
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
    multiplatformResourcesPackage = "io.github.dmitriy1892.resources.uikit"
}

android {
    namespace = "io.github.dmitriy1892.resources.uikit"
}