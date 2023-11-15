package resources.uikit.view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import resources.uikit.theme.AppTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableBox(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    menuModifier: Modifier = Modifier,
    isMenuVisible: () -> Boolean = { false },
    menuWidth: Dp,
    onMenuVisibilityChanged: (isVisible: Boolean) -> Unit,
    thresholdLimit: Float = 0.8f, // Must be between 0 and 1
    menuContent: @Composable BoxScope.() -> Unit = {},
    baseContent: @Composable BoxScope.() -> Unit,
) {
    val swipeableState = rememberSwipeableState(
        initialValue = if (isMenuVisible()) 1 else 0,
        confirmStateChange = { stateInt ->
            onMenuVisibilityChanged(stateInt == 1)
            true
        }
    )

    LaunchedEffect(key1 = isMenuVisible) {
        if (swipeableState.isAnimationRunning.not())
            swipeableState.animateTo(if (isMenuVisible()) 1 else 0)
    }


    val sizePx = with(LocalDensity.current) { menuWidth.toPx() }
    val anchors = mapOf(0f to 0, -sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = modifier
            .wrapContentSize()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(thresholdLimit) },
                orientation = Orientation.Horizontal,
            ),
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(
            modifier = menuModifier
                .wrapContentSize()
                .offset {
                    IntOffset(
                        x = menuWidth.roundToPx() + swipeableState.offset.value.roundToInt(),
                        y = 0
                    )
                },
            contentAlignment = Alignment.CenterEnd
        ) {
            menuContent()
        }
        Box(
            modifier = contentModifier
                .offset { IntOffset(x = swipeableState.offset.value.roundToInt(), y = 0) }
                .fillMaxWidth()
                .background(AppTheme.colors.bgWhite)
        ) {
            baseContent()
        }
    }
}