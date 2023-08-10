import extensions.libs

plugins {
    id("multiplatform-library-convention")
    id("kotlin-parcelize")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.bundles.common.feature)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.bundles.android.feature.view)
            }
        }
    }
}

android {
    buildFeatures {
        viewBinding = true
    }
}