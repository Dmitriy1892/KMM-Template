package resources.uikit.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val zero: Dp,
    val one: Dp,
)

val defaultDimens = Dimens(
    zero = 0.dp,
    one = 1.dp
)