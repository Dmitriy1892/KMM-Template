package io.github.dmitriy1892.di.impl

import io.github.dmitriy1892.kmm.utils.platform.PlatformConfiguration
import org.koin.core.Koin

fun KoinDiHolder.Companion.getInstance(): KoinDiHolder = this.getInstance(PlatformConfiguration())

fun KoinDiHolder.Companion.getKoin(): Koin = this.getInstance().koin