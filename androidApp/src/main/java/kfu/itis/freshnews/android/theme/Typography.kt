package kfu.itis.freshnews.android.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LocalTypography = staticCompositionLocalOf<FreshNewsTypography> {
    error("No typography provided")
}

class FreshNewsTypography(
    val welcomeTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    val screenHeadline: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.41).sp,
    ),
    val screenTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
    ),
    val cardTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 20.8.sp,
        letterSpacing = 0.sp,
    ),
    val cardSupportingText: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.sp,
    ),
    val cardContent: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.sp,
    ),
    val newsDescription: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp,
    ),
    val categoryName: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = 0.sp,
    ),
    val searchBarText: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.41).sp,
    ),
    val date: TextStyle = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        lineHeight = 20.8.sp,
        letterSpacing = 0.sp,
    ),
)
