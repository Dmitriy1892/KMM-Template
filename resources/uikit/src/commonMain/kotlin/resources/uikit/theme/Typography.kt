package resources.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.FontResource
import dev.icerock.moko.resources.compose.asFont
import resources.uikit.MR

@Immutable
object RobotoTypography

val RobotoTypography.h1Regular: TextStyle
    @Composable get() = TextStyle(
        fontFamily = RobotoFont,
        fontSize = 34.sp,
        fontWeight = FontWeight.Normal
    )

val RobotoFont: FontFamily
    @Composable get() = FontFamily(
        MR.fonts.Roboto.regular.asFontNotNull(weight = FontWeight.Normal),
        MR.fonts.Roboto.bold.asFontNotNull(weight = FontWeight.SemiBold),
        MR.fonts.Roboto.medium.asFontNotNull(weight = FontWeight.Medium)
    )

@Composable
private fun FontResource.asFontNotNull(
    weight: FontWeight = FontWeight.Normal,
    style: FontStyle = FontStyle.Normal
): Font = requireNotNull(this.asFont(weight = weight, style = style))