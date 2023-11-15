package resources.uikit.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

data class AppColors(
    val accentMain: Color,
    val bgWhite: Color
)

val lightColors = AppColors(
    accentMain = Color(0xFF006738),
    bgWhite = Color(0xFFFFFFFF)
)

val darkColors = AppColors(
    accentMain = Color(0xFF41B07D),
    bgWhite = Color(0xFF202020)
)

val lightColorScheme = lightColorScheme(
    primary = lightColors.accentMain,
    secondary = lightColors.accentMain,
    onSurface = lightColors.accentMain,
    surface = lightColors.bgWhite
)

val darkColorScheme = darkColorScheme(
    primary = darkColors.accentMain,
    secondary = darkColors.accentMain,
    onSurface = darkColors.accentMain,
    surface = darkColors.bgWhite
)