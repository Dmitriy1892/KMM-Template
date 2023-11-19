package conventionplugins

import extensions.androidMainDependencies
import extensions.commonMainDependencies
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class KmmFeatureComposeConvention : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kmm.library.project")
                apply("multiplatform-compose-setup")
                apply("android-compose-config") // NOTE: Requires for layout inspector composable tree working
                apply(libs.plugins.skie.get().pluginId)
            }

            commonMainDependencies {
                implementation(project(":resources:translations"))
                implementation(project(":resources:uikit"))

                implementation(project(":navigation:api"))

                implementation(project(":core:domain"))

                implementation(libs.bundles.common.feature)
            }

            androidMainDependencies {
                implementation(libs.bundles.android.feature)
            }
        }
    }
}