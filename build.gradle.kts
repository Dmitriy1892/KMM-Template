plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.google.ksp).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.ktorfit).apply(false)
    alias(libs.plugins.moko.resources).apply(false)
    alias(libs.plugins.skie).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory.asFile.get())
}