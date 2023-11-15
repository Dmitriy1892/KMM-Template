import extensions.commonMainDependencies
import extensions.iosMainDependencies
import extensions.iosRegularFramework

plugins {
    id("kmm.feature.compose")
    id("kmm.moko.resources")
    id("multiplatform-test-setup")
    alias(libs.plugins.ktorfit)
}

android {
    namespace = "di.impl"
}

iosRegularFramework {
    baseName = "SharedSDK"
    transitiveExport = false
    isStatic = true
    linkerOpts.add("-lsqlite3")

    export(project(":core:domain"))

    export(project(":navigation:api"))

    export(project(":feature:app"))
    export(project(":sample-feature:sample-one"))
    export(project(":sample-feature:main"))
    export(project(":sample-feature:tabview"))
    
    export(libs.kmm.utils)
    export(libs.kmm.mvvm.core)
    export(libs.kmm.mvvm.koin)
    export(libs.kmm.mvi.core)
    export(libs.kmm.mvi.kmm.mvvm)
    export(libs.decompose.core)
    export(libs.decompose.essenty.lifecycle)
    export(libs.moko.resources)
}

dependencies {
    add("kspCommonMainMetadata", libs.ktorfit.ksp.get())
}

commonMainDependencies {
    implementation(project(":core:data"))
    implementation(project(":core:datasource:local-database"))
    implementation(project(":core:datasource:local-datastore"))
    implementation(project(":core:datasource:network-rest"))

    implementation(project(":navigation:api"))
    implementation(project(":navigation:impl"))

    implementation(project(":cryptography:api"))
    implementation(project(":cryptography:impl"))

    implementation(project(":feature:app"))
    implementation(project(":sample-feature:sample-one"))
    implementation(project(":sample-feature:main"))
    implementation(project(":sample-feature:tabview"))

    implementation(libs.ktor.core)
    implementation(libs.ktorfit.lib)
    implementation(libs.androidx.preferences.datastore)
}

iosMainDependencies {
    api(project(":core:domain"))

    api(project(":navigation:api"))

    api(project(":feature:app"))
    api(project(":sample-feature:sample-one"))
    api(project(":sample-feature:main"))
    api(project(":sample-feature:tabview"))

    api(libs.kmm.utils)
    api(libs.kmm.mvvm.core)
    api(libs.kmm.mvvm.koin)
    api(libs.kmm.mvi.core)
    api(libs.kmm.mvi.kmm.mvvm)
    api(libs.decompose.core)
    api(libs.decompose.essenty.lifecycle)
    api(libs.moko.resources)
}