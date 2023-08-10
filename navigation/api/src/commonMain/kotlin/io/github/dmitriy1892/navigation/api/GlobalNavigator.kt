package io.github.dmitriy1892.navigation.api

interface GlobalNavigator {

    object Extras {
        private const val EXTRA_PACKAGE = "io.github.dmitriy1892"
    }

    /**
     * open some feature
     */
    fun openSomeGlobalFeature()
}