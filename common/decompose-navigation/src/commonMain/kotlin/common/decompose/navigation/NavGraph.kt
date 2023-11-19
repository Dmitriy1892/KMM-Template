package common.decompose.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.KSerializer

abstract class NavGraph<Config: Any, Destination: Any>(
    componentContext: ComponentContext,
    viewModelKey: String? = null
) : common.decompose.navigation.Destination(componentContext, viewModelKey ?: "") {

    protected open val handleBackButton: Boolean = true

    protected val navigation = StackNavigation<Config>()

    protected val childStack: Value<ChildStack<*, Destination>> by lazy {
        childStack(
            source = navigation,
            serializer = getConfigSerializer(),
            initialConfiguration = getInitialConfig(),
            handleBackButton = handleBackButton,
            childFactory = ::createDestination
        )
    }

    protected abstract fun getInitialConfig(): Config

    protected abstract fun getConfigSerializer(): KSerializer<Config>

    protected abstract fun createDestination(
        config: Config,
        componentContext: ComponentContext
    ): Destination
}