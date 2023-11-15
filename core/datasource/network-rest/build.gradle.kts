import extensions.androidMainDependencies
import extensions.commonMainDependencies
import extensions.iosMainDependencies

plugins {
    id("kmm.library.project")
    alias(libs.plugins.ktorfit)
}

android {
    namespace = "core.datasource.network.rest"
}

dependencies {
    add("kspCommonMainMetadata", libs.ktorfit.ksp.get())
}

commonMainDependencies {
    implementation(project(":core:data"))

    implementation(libs.bundles.ktor)
    implementation(libs.ktorfit.lib)
}

androidMainDependencies {
    implementation(libs.ktor.android)
}

iosMainDependencies {
    implementation(libs.ktor.ios)
    implementation(libs.ktor.darwin)
}