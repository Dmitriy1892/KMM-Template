package sample.feature.sample.one.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import sample.feature.sample.one.ui.SampleOneState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

class SampleOneStatePreviewParameterProvider : PreviewParameterProvider<SampleOneState> {

    override val values: Sequence<SampleOneState> = sequenceOf(
        SampleOneState(
            id = 1,
            isProgress = false,
            list = persistentListOf()
        )
    )
}