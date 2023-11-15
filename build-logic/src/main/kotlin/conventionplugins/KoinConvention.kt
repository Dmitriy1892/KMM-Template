package conventionplugins

import extensions.androidMainDependencies
import extensions.commonMainDependencies
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

class KoinConvention : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.google.ksp.get().pluginId)
            }

            commonMainDependencies {
                implementation(libs.koin.core)
                implementation(libs.koin.annotations)
            }

            androidMainDependencies {
                implementation(libs.koin.android)
            }

            dependencies {
                add("kspCommonMainMetadata", libs.koin.ksp.compiler.get())
//                add("kspAndroid", libs.koin.ksp.compiler.get())
//                add("kspIosX64", libs.koin.ksp.compiler.get())
//                add("kspIosArm64", libs.koin.ksp.compiler.get())
//                add("kspIosSimulatorArm64", libs.koin.ksp.compiler.get())
            }

            // WORKAROUND FOR KOIN KSP: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
            tasks.withType<KotlinCompile<*>>().configureEach {
                if (name != "kspCommonMainKotlinMetadata") {
                    dependsOn("kspCommonMainKotlinMetadata")
                }
            }
            afterEvaluate {
                tasks
                    .filter { it.name.contains("SourcesJar", true) }
                    .forEach {
                        println("SourceJarTask====>${it.name}")
                        it.dependsOn("kspCommonMainKotlinMetadata")
                    }
            }
        }
    }
}