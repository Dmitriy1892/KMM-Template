package sample.feature.sample.one.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter

@Preview(showSystemUi = true)
@Composable
fun SampleOneScreenPreview(
    @PreviewParameter(
        SampleOneStatePreviewParameterProvider::class,
        limit = 1
    )
    state: SampleOneState
) {
    val innerState by remember {
        mutableStateOf(state)
    }
    SampleOneScreenContent(
        passedId = innerState.id,
        onNext = {  },
        content = {
            ListWithFab(
                list = remember {{ innerState.list }},
                onItemClick = {}
            )
        }
    )
}