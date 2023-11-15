package sample.feature.tabview.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectAsStateWithEssentyLifecycle
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectSideEffect
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectSideEffectWithEssentyLifecycle
import io.github.dmitriy1892.kmm.mvvm.compose.kmmViewModel
import sample.feature.tabview.main.TabViewViewModel
import sample.feature.tabview.main.model.MenuItem
import sample.feature.tabview.main.model.TabViewState
import resources.uikit.theme.AppTheme

@Composable
fun TabViewScreen(
    selectedMenuItem: () -> MenuItem,
    openTabContent: (MenuItem) -> Unit,
    viewModel: TabViewViewModel = kmmViewModel(),
    tabContent: @Composable BoxScope.() -> Unit
) {
    viewModel.sideEffectFlow.collectSideEffectWithEssentyLifecycle { sideEffect ->

    }

    val state by viewModel.stateFlow.collectAsStateWithEssentyLifecycle()

    TabViewScreenContent(
        state = state,
        selectedMenuItem = selectedMenuItem,
        onItemClicked = openTabContent,
        content = tabContent
    )
}

@Composable
fun TabViewScreenContent(
    state: TabViewState,
    selectedMenuItem: () -> MenuItem,
    onItemClicked: (MenuItem) -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            TabMenu(
                selectedMenuItem = selectedMenuItem,
                onItemClicked = onItemClicked
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(it),
            content = content
        )
    }
}

@Composable
private fun TabMenu(
    selectedMenuItem: () -> MenuItem,
    onItemClicked: (MenuItem) -> Unit
) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = AppTheme.colors.bgWhite,
        elevation = 20.dp
    ) {
        TabItem(
            selected = remember {{ selectedMenuItem() == MenuItem.ADD }},
            iconRes = Icons.Default.Add,
            onClick = remember {{ onItemClicked(MenuItem.ADD) }}
        )

        TabItem(
            selected = remember {{ selectedMenuItem() == MenuItem.PROFILE }},
            iconRes = Icons.Default.AccountCircle,
            onClick = remember {{ onItemClicked(MenuItem.PROFILE) }}
        )

        TabItem(
            selected = remember {{ selectedMenuItem() == MenuItem.SETTINGS }},
            iconRes = Icons.Default.Build,
            onClick = remember {{ onItemClicked(MenuItem.SETTINGS) }}
        )
    }
}

@Composable
private fun RowScope.TabItem(
    selected: () -> Boolean,
    iconRes: ImageVector,
    onClick: () -> Unit,
) {
    BottomNavigationItem(
        selected = selected(),
        onClick = onClick,
        icon = {
            Image(
                modifier = Modifier.align(Alignment.CenterVertically),
                imageVector = iconRes,
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (selected()) Color.Magenta else Color.Black)
            )
        },
        selectedContentColor = Color.Magenta,
        unselectedContentColor = Color.Black
    )
}