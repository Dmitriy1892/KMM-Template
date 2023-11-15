import extensions.commonMainDependencies

plugins {
    id("kmm.library.project")
    id("multiplatform-test-setup")
}

android {
    namespace = "cryptography.impl"
}

commonMainDependencies {
    implementation(libs.crypto.kmp.diglol)
    implementation(libs.ktor.json)
}