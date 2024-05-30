package kfu.itis.freshnews.android.navigation

sealed class NestedScreen(
    val route: String,
    val argKey: String,
) {

    object DetailsScreen : NestedScreen(
        route = FreshNewsRoutes.DETAILS_SCREEN_ROUTE,
        argKey = "article",
    )
}
