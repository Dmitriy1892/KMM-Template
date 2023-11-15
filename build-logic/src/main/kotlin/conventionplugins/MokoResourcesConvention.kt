package conventionplugins

import extensions.androidMainDependencies
import extensions.androidOptions
import extensions.commonMainDependencies
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class MokoResourcesConvention : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kmm.library.base")
                apply("multiplatform-compose-setup")
                apply(libs.plugins.moko.resources.get().pluginId)
            }

            androidOptions {
                sourceSets {
                    getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
                }
            }

            commonMainDependencies {
                api(libs.moko.resources)
                api(libs.moko.resources.compose)
            }

            androidMainDependencies {
                implementation(libs.androidx.core)
            }
        }
    }
}