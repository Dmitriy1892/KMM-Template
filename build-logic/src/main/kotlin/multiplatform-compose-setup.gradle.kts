import extensions.libs

plugins {
    id("multiplatform-setup")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        commonMain.dependencies {
            implementation(compose.animation)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            implementation(libs.kotlinx.immutablecollections)
            implementation(libs.composeImageLoader)
        }

        androidMain.dependencies {
            implementation(libs.bundles.android.compose)
        }
    }
}