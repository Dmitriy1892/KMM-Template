package sample.feature.tabview.main

import io.github.dmitriy1892.kmm.mvi.kmm.mvvm.MviViewModel
import io.github.dmitriy1892.kmm.mvvm.core.BaseViewModel
import io.github.dmitriy1892.kmm.mvvm.core.VIEW_MODEL_KEY_PREFIX
import sample.feature.tabview.main.model.TabViewSideEffect
import sample.feature.tabview.main.model.TabViewState
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory(binds = [BaseViewModel::class])
@Named(value = "$VIEW_MODEL_KEY_PREFIX:sample.feature.tabview.main.TabViewViewModel")
class TabViewViewModel : MviViewModel<TabViewState, TabViewSideEffect>() {

    override val initialState: TabViewState = TabViewState()
}