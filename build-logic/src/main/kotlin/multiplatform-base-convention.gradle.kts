import extensions.projectJavaVersion

plugins {
    kotlin("multiplatform")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = projectJavaVersion.toString()
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}