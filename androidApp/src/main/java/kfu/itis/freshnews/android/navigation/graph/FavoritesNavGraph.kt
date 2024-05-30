package kfu.itis.freshnews.android.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kfu.itis.freshnews.android.feature.favorites.FavoritesScreen
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes

fun NavGraphBuilder.favoritesNavGraph(navController: NavController) {
    navigation(
        route = FreshNewsRoutes.FAVORITES_GRAPH_ROUTE,
        startDestination = FreshNewsRoutes.FAVORITES_SCREEN_ROUTE,
    ) {
        composable(route = FreshNewsRoutes.FAVORITES_SCREEN_ROUTE) {
            FavoritesScreen(navController = navController)
        }
    }
}
