package extensions

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework

fun Project.iosRegularFramework(block: Framework.() -> Unit) {
    with(kotlinMultiplatformExtension) {
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach {
            it.binaries.framework {
                block()
            }
        }
    }
}