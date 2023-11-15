import extensions.androidOptions
import extensions.buildComposeMetricsParameters
import extensions.libs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

androidOptions {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.set(buildComposeMetricsParameters())
    }
}