package kfu.itis.freshnews.android.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalColors = staticCompositionLocalOf<FreshNewsColors> {
    error("No colors provided")
}

sealed interface FreshNewsColors {
    val primary: Color
    val onPrimary: Color
    val onPrimaryVariant: Color
    val accent: Color
    val outline: Color
    val onOutline: Color
    val bottomBarContainer: Color
    val bottomBarItemUnselected: Color
    val cardTransparent: Color

    class FreshNewsLightThemeColors(
        override val primary: Color = Color(0xFFFFFFFF),
        override val onPrimary: Color = Color(0xFF000000),
        override val onPrimaryVariant: Color = Color(0xFF2E0505),
        override val accent: Color = Color(0xFF0080FF),
        override val outline: Color = Color(0x1F767680),
        override val onOutline: Color = Color( 0x993C3C43),
        override val bottomBarContainer: Color = Color(0xFFF9F9F9),
        override val bottomBarItemUnselected: Color = Color(0xFF959595),
        override val cardTransparent: Color = Color(0xFFF5F5F5),
    ) : FreshNewsColors

    class FreshNewsDarkThemeColors(
        override val primary: Color = Color(0xFFFFFFFF),
        override val onPrimary: Color = Color(0xFF000000),
        override val onPrimaryVariant: Color = Color(0xFF2E0505),
        override val accent: Color = Color(0xFF0080FF),
        override val outline: Color = Color(0x1F767680),
        override val onOutline: Color = Color( 0x993C3C43),
        override val bottomBarContainer: Color = Color(0xFFF9F9F9),
        override val bottomBarItemUnselected: Color = Color(0xFF959595),
        override val cardTransparent: Color = Color(0xFFF5F5F5),
    ) : FreshNewsColors
}
