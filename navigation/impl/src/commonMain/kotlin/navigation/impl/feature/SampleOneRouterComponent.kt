package navigation.impl.feature

import androidx.compose.runtime.Composable
import io.github.dmitriy1892.kmm.mvvm.core.viewModelKey
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import sample.feature.sample.one.ui.SampleOneScreen
import sample.feature.sample.one.ui.SampleOneViewModel
import navigation.api.feature.SampleOneRouter
import navigation.impl.BaseDestinationComponent
import navigation.impl.navgraph.root.RootConfig

class SampleOneRouterComponent(
    componentContext: ComponentContext,
    private val screenId: Int,
    private val navigation: StackNavigation<RootConfig>
) : BaseDestinationComponent(componentContext), SampleOneRouter {

    override val viewModelKey: String = SampleOneViewModel::class.viewModelKey()

    override fun openNextFeature() {
        navigation.push(RootConfig.Main2Config)
    }

    @Composable
    override fun Content() {
        SampleOneScreen(
            sampleId = screenId,
            navigateToNext = ::openNextFeature
        )
    }
}