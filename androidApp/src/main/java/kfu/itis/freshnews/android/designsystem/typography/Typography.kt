package kfu.itis.freshnews.android.designsystem.typography

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LocalTypography = staticCompositionLocalOf<FreshNewsTypography> { error("No typography provided") }

data class FreshNewsTypography(
    val screenHeading: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    val cardTitle: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
    ),
    val cardSupportingText: TextStyle = TextStyle(
        fontSize = 14.sp,
    ),
    val category: TextStyle = TextStyle(
        fontSize = 14.sp,
    ),
    val button: TextStyle = TextStyle(
        fontSize = 14.sp,
    ),
    val commonText: TextStyle = TextStyle(
        fontSize = 16.sp,
    ),
    val bottomNavigationText: TextStyle = TextStyle(
        fontSize = 14.sp,
    ),
)
