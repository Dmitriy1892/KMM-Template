package extensions

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add("implementation", dependency)
}

internal fun DependencyHandlerScope.implementation(
    dependency: ProjectDependency
) {
    add("implementation", dependency)
}

internal fun DependencyHandlerScope.kapt(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add("kapt", dependency)
}

internal fun DependencyHandlerScope.kaptAndroidTest(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add("kaptAndroidTest", dependency)
}