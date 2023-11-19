import extensions.commonMainDependencies

plugins {
    id("kmm.library.base")
    id("multiplatform-compose-setup")
}

android {
    namespace = "common.decompose.navigation"
}

commonMainDependencies {
    implementation(libs.decompose.core)
    implementation(libs.decompose.compose.multiplatform)
    implementation(libs.decompose.essenty.lifecycle)
    implementation(libs.koin.core)
    implementation(libs.kmm.mvvm.core)
    implementation(libs.kmm.mvi.compose.multiplatform)
}