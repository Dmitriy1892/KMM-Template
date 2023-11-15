package sample.feature.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreenContent(
        screenName = "Screen 1",
        state = MainState(
            someText = "Some text",
            isProgress = false
        ),
        onClick = {}
    )
}