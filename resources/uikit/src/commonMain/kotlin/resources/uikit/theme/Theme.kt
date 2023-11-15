package resources.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier

@Composable
fun AppTheme(
    isDarkThemeEnabled: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val typography = RobotoTypography
    val dimens = defaultDimens
    val appColors = if (isDarkThemeEnabled) darkColors else lightColors
    val appColorScheme = if (isDarkThemeEnabled) darkColorScheme else lightColorScheme

    CompositionLocalProvider(
        LocalAppTypography provides typography,
        LocalAppDimens provides dimens,
        LocalAppColors provides appColors,
        LocalAppColorsScheme provides appColorScheme
    ) {
        SystemAppearance(isDarkThemeEnabled.not())
        MaterialTheme(
            colorScheme = appColorScheme
        ) {
            Surface(
                modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing),
                contentColor = appColors.bgWhite
            ) {
                content()
            }
        }
    }
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)

val LocalAppTypography = staticCompositionLocalOf { RobotoTypography }
val LocalAppDimens = staticCompositionLocalOf { defaultDimens }
val LocalAppColors = staticCompositionLocalOf { lightColors }
val LocalAppColorsScheme = staticCompositionLocalOf { lightColorScheme }

object AppTheme {

    val typography: RobotoTypography
        @Composable
        get() = LocalAppTypography.current

    val dimens: Dimens
        @Composable
        get() = LocalAppDimens.current

    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
}