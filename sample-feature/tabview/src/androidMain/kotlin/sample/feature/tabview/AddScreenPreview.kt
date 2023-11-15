package sample.feature.tabview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sample.feature.tabview.tabs.add.view.AddScreenContent
import sample.feature.tabview.tabs.add.model.AddState

@Preview(showSystemUi = true)
@Composable
fun AddScreenPreview() {

    AddScreenContent(state = AddState(screenIndex = 0)) {

    }
}