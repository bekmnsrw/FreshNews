package kfu.itis.freshnews.android.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kfu.itis.freshnews.android.feature.details.DetailsScreen
import kfu.itis.freshnews.android.feature.favorites.FavoritesScreen
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes
import kfu.itis.freshnews.android.navigation.NestedScreen

fun NavGraphBuilder.favoritesNavGraph(navController: NavController) {
    navigation(
        route = FreshNewsRoutes.FAVORITES_GRAPH_ROUTE,
        startDestination = FreshNewsRoutes.FAVORITES_SCREEN_ROUTE,
    ) {
        composable(route = FreshNewsRoutes.FAVORITES_SCREEN_ROUTE) {
            FavoritesScreen(navController = navController)
        }
        composable(route = NestedScreen.DetailsScreenFromFavorites.route) {
            DetailsScreen(
                navController = navController,
                favoriteArticleId = navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Int>(NestedScreen.DetailsScreenFromFavorites.argKey),
            )
        }
    }
}
