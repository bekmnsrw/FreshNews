package kfu.itis.freshnews.android.designsystem.color

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalColors = staticCompositionLocalOf<FreshNewsColors> { error("No colors provided") }

sealed interface FreshNewsColors {
    val background: Color
    val mainText: Color
    val supportingText: Color
    val accent: Color
    val error: Color
    val outline: Color
    val bottomBar: Color
    val bottomBarSelected: Color
    val bottomBarUnselected: Color
    val iconContainer: Color
    val iconContent: Color
    val buttonContent: Color
    val buttonContainer: Color

    class FreshNewsLightThemeColors(
        override val background: Color = Color(255, 255,255),
        override val mainText: Color = Color(0, 0, 0),
        override val supportingText: Color = Color(142, 142, 147),
        override val accent: Color = Color(0, 122, 255),
        override val error: Color = Color(255, 59, 48),
        override val outline: Color = Color(142, 142, 147),
        override val bottomBar: Color = Color(0xFFF9F9F9),
        override val bottomBarSelected: Color = Color(0, 122, 255),
        override val bottomBarUnselected: Color = Color(0xFF959595),
        override val iconContainer: Color = Color(0, 122, 255),
        override val iconContent: Color = Color(255, 255,255),
        override val buttonContainer: Color = Color(0, 122, 255),
        override val buttonContent: Color = Color(255, 255, 255),
    ) : FreshNewsColors

    class FreshNewsDarkThemeColors(
        override val background: Color = Color(0, 0,0),
        override val mainText: Color = Color(255, 255, 255),
        override val supportingText: Color = Color(142, 142, 147),
        override val accent: Color = Color(10, 132, 255),
        override val error: Color = Color(255, 69, 58),
        override val outline: Color = Color(142, 142, 147),
        override val bottomBar: Color = Color(28, 28, 30),
        override val bottomBarSelected: Color = Color(0, 122, 255),
        override val bottomBarUnselected: Color = Color(0xFF959595),
        override val iconContainer: Color = Color(0, 122, 255),
        override val iconContent: Color = Color(255, 255,255),
        override val buttonContainer: Color = Color(0, 122, 255),
        override val buttonContent: Color = Color(255, 255, 255),
    ) : FreshNewsColors
}
