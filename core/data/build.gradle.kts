import extensions.commonMainDependencies

plugins {
    id("kmm.library.project")
}

android {
    namespace = "core.data"
}

commonMainDependencies {
    api(project(":core:domain"))
}