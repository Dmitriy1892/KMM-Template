import extensions.libs

plugins {
    id("android-library-setup")
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonTest {
            dependencies {
                implementation(libs.kotlin.test.common)
                implementation(libs.kotlin.test.annotations.common)
                implementation(libs.kotlin.test.coroutines.common)
                implementation(libs.koin.test)
            }
        }

        val androidInstrumentedTest by getting {
            dependencies {
                implementation(libs.androidx.test.runner)
                implementation(libs.koin.test.android)
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(libs.kotlin.test.junit)
                implementation(libs.koin.test.android)
            }
        }
    }
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets["androidTest"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}