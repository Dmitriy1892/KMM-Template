package io.github.dmitriy1892.android

import android.app.Application
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import di.impl.KoinDiHolder

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinDiHolder.getInstance(PlatformConfiguration(this))
    }
}