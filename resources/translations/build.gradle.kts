plugins {
    id("kmm.moko.resources")
}

android {
    namespace = "resources.translations"
}

multiplatformResources {
    multiplatformResourcesPackage = "resources.translations"
    disableStaticFrameworkWarning = true
}