package navigation.impl.feature

import androidx.compose.runtime.Composable
import io.github.dmitriy1892.kmm.mvvm.core.viewModelKey
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import common.decompose.navigation.Destination
import sample.feature.sample.one.ui.SampleOneScreen
import sample.feature.sample.one.ui.SampleOneViewModel
import navigation.api.feature.SampleOneRouter
import navigation.impl.navgraph.root.RootConfig

class SampleOneRouterComponent(
    componentContext: ComponentContext,
    private val screenId: Int,
    private val navigation: StackNavigation<RootConfig>
) : Destination(
    componentContext = componentContext,
    viewModelKey = SampleOneViewModel::class.viewModelKey()
), SampleOneRouter {

    override fun openNextFeature() {
        navigation.push(RootConfig.Main2Config)
    }

    @Composable
    override fun ContentScreen() {
        SampleOneScreen(
            sampleId = screenId,
            navigateToNext = ::openNextFeature
        )
    }
}