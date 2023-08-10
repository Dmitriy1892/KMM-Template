package io.github.dmitriy1892.navigation.impl

import io.github.dmitriy1892.navigation.api.GlobalNavigator

class GlobalNavigatorImpl : GlobalNavigator {

    override fun openSomeGlobalFeature() {
        println("Some global feature opened")
    }
}