package conventionplugins

import extensions.commonMainDependencies
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class KmmLibraryConvention : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kmm.library.base")
                apply("kmm.koin.annotations")
            }

            commonMainDependencies {
                implementation(project(":common:platform"))
                implementation(project(":common:utils"))

                implementation(project(":di:api"))
            }
        }
    }
}