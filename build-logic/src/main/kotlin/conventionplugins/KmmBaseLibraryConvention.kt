package conventionplugins

import extensions.androidOptions
import extensions.commonMainDependencies
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class KmmBaseLibraryConvention : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("android-library-setup")
                apply("multiplatform-setup")
                apply(libs.plugins.kotlinx.serialization.get().pluginId)
            }

            androidOptions {
                buildFeatures {
                    buildConfig = true
                }
            }

            commonMainDependencies {
                implementation(libs.bundles.common.library)
            }
        }
    }
}