package extensions

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.gradle.kotlin.dsl.findByType

fun Project.commonMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(kotlinMultiplatformExtension) {
        sourceSets.commonMain.dependencies(block)
    }
}

fun Project.androidMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(kotlinMultiplatformExtension) {
        sourceSets.androidMain.dependencies(block)
    }
}

fun Project.iosMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(kotlinMultiplatformExtension) {
        sourceSets.iosMain.dependencies(block)
    }
}

internal val Project.kotlinMultiplatformExtension: KotlinMultiplatformExtension
    get() = requireNotNull(extensions.findByType(KotlinMultiplatformExtension::class)) {
        "\"Project.kotlinMultiplatformExtension\" value may be called only from multiplatform module"
    }