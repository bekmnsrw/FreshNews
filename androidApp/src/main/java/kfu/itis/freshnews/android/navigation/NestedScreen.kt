package kfu.itis.freshnews.android.navigation

sealed class NestedScreen(
    val route: String,
    val argKey: String,
) {

    object DetailsScreenFromHome : NestedScreen(
        route = FreshNewsRoutes.DETAILS_SCREEN_ROUTE_FROM_HOME,
        argKey = "article",
    )

    object DetailsScreenFromFavorites : NestedScreen(
        route = FreshNewsRoutes.DETAILS_SCREEN_ROUTE_FROM_FAVORITES,
        argKey = "favorites_id",
    )
}
