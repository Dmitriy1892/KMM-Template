import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    mavenLocal()
    google()
    gradlePluginPortal()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(libs.gradleplugins.android)
    implementation(libs.gradleplugins.compose)
    implementation(libs.gradleplugins.google.ksp)
    implementation(libs.gradleplugins.kotlin)
    implementation(libs.gradleplugins.kotlinx.serialization)
    implementation(libs.gradleplugins.moko.resources)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

private val projectJavaVersion: JavaVersion = JavaVersion.toVersion(libs.versions.java.get())

java {
    sourceCompatibility = projectJavaVersion
    targetCompatibility = projectJavaVersion
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = projectJavaVersion.toString()
}

gradlePlugin {
    plugins {
        register("mokoResourcesConvention") {
            id = "kmm.moko.resources"
            implementationClass = "conventionplugins.MokoResourcesConvention"
        }

        register("koinConvention") {
            id = "kmm.koin.annotations"
            implementationClass = "conventionplugins.KoinConvention"
        }

        register("kmmBaseLibraryConvention") {
            id = "kmm.library.base"
            implementationClass = "conventionplugins.KmmBaseLibraryConvention"
        }

        register("KmmLibraryConvention") {
            id = "kmm.library.project"
            implementationClass = "conventionplugins.KmmLibraryConvention"
        }

        register("kmmFeatureComposeConvention") {
            id = "kmm.feature.compose"
            implementationClass = "conventionplugins.KmmFeatureComposeConvention"
        }

        register("kmmApplicationConvention") {
            id = "kmm.application.compose"
            implementationClass = "conventionplugins.KmmApplicationConvention"
        }
    }
}