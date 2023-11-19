package sample.feature.sample.one.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectSideEffect
import io.github.dmitriy1892.kmm.mvvm.compose.kmmViewModel
import io.github.dmitriy1892.kmm.mvvm.koin.factory.KoinAssistedViewModelFactory
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectAsStateWithLifecycle
import io.github.dmitriy1892.kmm.mvi.compose.multiplatform.collectSideEffectWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf
import resources.uikit.MR

@Composable
fun SampleOneScreen(
    sampleId: Int,
    navigateToNext: () -> Unit,
    viewModel: SampleOneViewModel = kmmViewModel(
        factory = KoinAssistedViewModelFactory(parametersOf(sampleId))
    ),
) {
    val state: SampleOneState by viewModel.stateFlow.collectAsStateWithLifecycle()
    viewModel.sideEffectFlow.collectSideEffectWithLifecycle { sideEffect ->
        // TODO
    }

    val id = remember(state.id) { state.id }

    SampleOneScreenContent(
        passedId = id,
        onNext = navigateToNext,
    ) {
        ListWithFab(
            list = remember {{ state.list }},
            onItemClick = remember {{ viewModel.onItemClick(it) }},
        )
    }
}

@Composable
fun SampleOneScreenContent(
    passedId: Int,
    onNext: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Injected param id = $passedId",
        )
        Button(onClick = onNext) {
            Text(text = "Next")
        }

        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

@Composable
fun BoxScope.ListWithFab(
    list: () -> ImmutableList<UiItem>,
    onItemClick: (index: Int) -> Unit
) {
    val listState = rememberLazyListState()

    ItemsList(
        list = list,
        listState = listState,
        onItemClick = onItemClick
    )

    val coroutineScope = rememberCoroutineScope()

    FloatingActionButton(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(20.dp),
        onClick = remember {{
            coroutineScope.launch {
                listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
            }
        }}
    ) {
        Image(
            modifier = Modifier
                .size(40.dp),
            painter = painterResource(MR.images.ic_arrow_down),
            contentDescription = null
        )
    }
}

@Composable
private fun ItemsList(
    list: () -> ImmutableList<UiItem>,
    listState: LazyListState,
    onItemClick: (index: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState
    ) {
        itemsIndexed(
            items = list(),
            key = { _, uiItem -> uiItem.id }
        ) { index, item ->
            val click = remember {{ onItemClick(index) }}
            when (item.type) {
                UiItemType.PARENT -> ParentItemView(item, click)
                UiItemType.CHILD -> ChildItemView(item, click)
                UiItemType.SUB_CHILD -> SubChildItemView(item, click)
            }
        }
    }
}

@Composable
private fun ParentItemView(
    model: UiItem,
    onItemClick: () -> Unit
) {
    RowItem(
        text = model.name,
        fontSize = 20.sp,
        onClick = onItemClick
    )
}

@Composable
private fun ChildItemView(
    model: UiItem,
    onItemClick: () -> Unit
) {
    RowItem(
        text = model.name,
        fontSize = 16.sp,
        textPaddingStart = 20.dp,
        onClick = onItemClick
    )
}

@Composable
private fun SubChildItemView(
    model: UiItem,
    onItemClick: () -> Unit
) {
    RowItem(
        modifier = Modifier,
        text = model.name,
        fontSize = 12.sp,
        textPaddingStart = 40.dp,
        isImageVisible = model.isVisible,
        onClick = onItemClick
    )
}

@Composable
private fun RowItem(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    textPaddingStart: Dp = 0.dp,
    startImage: () -> ImageResource = remember {{ MR.images.ic_check }},
    isImageVisible: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .clickable { onClick() }
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(start = textPaddingStart))
        if (isImageVisible) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(startImage()),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }

        Text(
            modifier = Modifier.padding(
                start = if (isImageVisible) 10.dp else 0.dp
            ),
            text = text,
            fontSize = fontSize
        )
    }
}