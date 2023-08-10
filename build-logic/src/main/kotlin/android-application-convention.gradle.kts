import extensions.getApplicationVersionCode
import extensions.getApplicationVersionName

plugins {
    id("com.android.application")
    id("android-base-convention")
    id("multiplatform-base-convention")
}

android {
    defaultConfig {
        versionCode = getApplicationVersionCode()
        versionName = getApplicationVersionName()
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/{AL2.0,LGPL2.1}",
                "META-INF/DEPENDENCIES",
                "META-INF/DEPENDENCIES.txt",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/LICENSE-FIREBASE.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/*.kotlin_module"
            )
        }
    }
}