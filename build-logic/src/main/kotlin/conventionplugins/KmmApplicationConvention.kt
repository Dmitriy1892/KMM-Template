package conventionplugins

import extensions.androidMainDependencies
import extensions.commonMainDependencies
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class KmmApplicationConvention : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("android-application-setup")
                apply("multiplatform-compose-setup")
                apply("kmm.koin.annotations")
            }

            commonMainDependencies {
                implementation(project(":common:platform"))
                implementation(project(":common:utils"))

                implementation(project(":resources:uikit"))
                implementation(project(":resources:translations"))

                implementation(project(":di:api"))
                implementation(project(":di:impl"))

                implementation(project(":navigation:api"))
                implementation(project(":navigation:impl"))

                implementation(project(":core:domain"))

                implementation(project(":feature:app"))

                implementation(libs.bundles.common.library)
                implementation(libs.bundles.common.feature)
            }

            androidMainDependencies {
                implementation(libs.bundles.android.feature)
            }
        }
    }
}