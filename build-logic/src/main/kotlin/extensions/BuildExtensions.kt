package extensions

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.konan.properties.Properties

val Project.projectJavaVersion: JavaVersion
    get() = JavaVersion.toVersion(libs.versions.java.get().toInt())

internal val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

fun Project.getApplicationVersionCode(): Int = useProperties { props ->
    val versionMajor = props.getProperty("VERSION_MAJOR").toInt()
    val versionMinor = props.getProperty("VERSION_MINOR").toInt()
    val versionPatch = props.getProperty("VERSION_PATCH").toInt()

    versionMajor * 10000 + versionMinor * 100 + versionPatch
}

fun Project.getApplicationVersionName(): String = useProperties { props ->
    val versionMajor = props.getProperty("VERSION_MAJOR")
    val versionMinor = props.getProperty("VERSION_MINOR")
    val versionPatch = props.getProperty("VERSION_PATCH")
    "$versionMajor.$versionMinor.$versionPatch"
}

private fun <T> Project.useProperties(block: (Properties) -> T): T {
    val propertiesFile = project.rootProject.file("version.properties")
    return propertiesFile.reader().use {
        val props = Properties().apply { load(it) }
        block(props)
    }
}
