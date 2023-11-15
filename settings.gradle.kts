pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KMM-Template"

includeBuild("build-logic")

include(":androidApp")

include(":resources:translations")
include(":resources:uikit")

include(":common:platform")
include(":common:utils")

include(":core:domain")
include(":core:data")
include(":core:datasource:local-database")
include(":core:datasource:local-datastore")
include(":core:datasource:network-rest")

include(":cryptography:api")
include(":cryptography:impl")

include(":navigation:api")
include(":navigation:impl")

include(":di:api")
include(":di:impl")

include(":feature:app")
include(":sample-feature:sample-one")
include(":sample-feature:main")
include(":sample-feature:tabview")
include(":sampleAndroidApp")