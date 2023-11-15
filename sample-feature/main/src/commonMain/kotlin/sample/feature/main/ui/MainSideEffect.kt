package sample.feature.main.ui

sealed class MainSideEffect {

    class OpenSampleOneScreen(val id: Int) : MainSideEffect()
}