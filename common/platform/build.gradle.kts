import extensions.androidMainDependencies
import extensions.commonMainDependencies

plugins {
    id("kmm.library.base")
}

android {
    namespace = "common.platform"

    buildFeatures {
        viewBinding = true
    }
}

commonMainDependencies {
    implementation(libs.napier)
}

androidMainDependencies {
    implementation(libs.bundles.android.view)
    implementation(libs.bundles.android.feature)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.exifinterface)
}