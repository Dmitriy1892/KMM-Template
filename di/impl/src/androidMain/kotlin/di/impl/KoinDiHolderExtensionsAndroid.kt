package di.impl

import android.content.Context
import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import org.koin.core.Koin

fun KoinDiHolder.Companion.getInstance(applicationContext: Context): KoinDiHolder =
    this.getInstance(PlatformConfiguration(applicationContext))

fun KoinDiHolder.Companion.getKoin(applicationContext: Context): Koin =
    this.getInstance(applicationContext).koin