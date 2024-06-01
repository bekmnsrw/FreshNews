package kfu.itis.freshnews.android.navigation.bottombar

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import kfu.itis.freshnews.android.R
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes
import kfu.itis.freshnews.android.designsystem.icon.FreshNewsIcons

sealed class TabItem(
    val route: String,
    @StringRes val name: Int,
    val icon: ImageVector,
) {

    object Home : TabItem(
        route = FreshNewsRoutes.HOME_GRAPH_ROUTE,
        name = R.string.bottom_bar_home,
        icon = FreshNewsIcons.HOME,
    )

    object Favorite : TabItem(
        route = FreshNewsRoutes.FAVORITES_GRAPH_ROUTE,
        name = R.string.bottom_bar_favorite,
        icon = FreshNewsIcons.FAVORITE_FILL,
    )

    object Profile : TabItem(
        route = FreshNewsRoutes.PROFILE_GRAPH_ROUTE,
        name = R.string.bottom_bar_profile,
        icon = FreshNewsIcons.PROFILE,
    )
}
