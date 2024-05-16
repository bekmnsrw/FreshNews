package kfu.itis.freshnews.android.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import kfu.itis.freshnews.android.R
import kfu.itis.freshnews.android.theme.FreshNewsIcons

sealed class BottomBarItem(
    @StringRes val name: Int,
    val icon: ImageVector,
) {

    object Home : BottomBarItem(
        name = R.string.bottom_bar_home,
        icon = FreshNewsIcons.HOME,
    )

    object Favorite : BottomBarItem(
        name = R.string.bottom_bar_favorite,
        icon = FreshNewsIcons.FAVORITE,
    )

    object Profile : BottomBarItem(
        name = R.string.bottom_bar_profile,
        icon = FreshNewsIcons.PROFILE,
    )
}
