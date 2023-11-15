import extensions.commonMainDependencies

plugins {
    id("kmm.library.project")
    id("multiplatform-test-setup")
}

android {
    namespace = "core.datasource.local.datastore"
}

commonMainDependencies {
    implementation(project(":core:data"))

    implementation(libs.androidx.preferences.datastore)
}