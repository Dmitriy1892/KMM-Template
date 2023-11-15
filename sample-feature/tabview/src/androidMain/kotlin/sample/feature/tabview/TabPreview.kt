package sample.feature.tabview

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import sample.feature.tabview.main.model.MenuItem
import sample.feature.tabview.main.model.TabViewState
import sample.feature.tabview.main.view.TabViewScreenContent
import resources.uikit.theme.AppTheme

@Preview(showSystemUi = true)
@Composable
fun TabViewScreenPreview() {
    AppTheme {
        TabViewScreenContent(
            state = TabViewState(),
            selectedMenuItem = remember {{ MenuItem.PROFILE }},
            onItemClicked = {}
        ) {
            Text(text = "SOME TEXT", modifier = Modifier.align(Alignment.TopCenter))
            Text(text = "SOME TEXT", modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}