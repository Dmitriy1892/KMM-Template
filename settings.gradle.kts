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

include(":di:api")
include(":di:impl")

include(":navigation:api")
include(":navigation:impl")

include(":core:domain")
include(":core:data")
include(":core:datasource:local-database")
include(":core:datasource:network")