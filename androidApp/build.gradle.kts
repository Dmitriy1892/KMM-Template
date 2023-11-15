import extensions.androidMainDependencies

plugins {
    id("kmm.application.compose")
}

android {
    namespace = "io.github.dmitriy1892.android"

    defaultConfig {
        applicationId = "io.github.dmitriy1892.android"
    }

    signingConfigs {
        create("release") {
            keyAlias = "key0"
            keyPassword = "sample"
            storeFile = file("../key/kmm-sample-key.jks")
            storePassword = "sample"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard/proguard-android.pro",
                "proguard/proguard-crypto.pro",
                "proguard/proguard-kmm-libs.pro",
                "proguard/proguard-kotlin.pro",
                "proguard/proguard-kotlin-coroutines.pro",
                "proguard/proguard-kotlinx-serialization.pro",
                "proguard/proguard-sqlcipher.pro"
            )
        }
    }
}

androidMainDependencies {
    implementation(project(":core:data"))
    implementation(project(":core:datasource:network-rest"))
    implementation(project(":core:datasource:local-database"))

    implementation(libs.androidx.activityCompose)
}