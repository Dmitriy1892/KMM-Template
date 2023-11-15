plugins {
    id("kmm.moko.resources")
}

android {
    namespace = "resources.uikit"
}

multiplatformResources {
    multiplatformResourcesPackage = "resources.uikit"
    disableStaticFrameworkWarning = true
}