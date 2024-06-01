package kfu.itis.freshnews.android.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kfu.itis.freshnews.android.feature.auth.AuthScreen
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
) {
    navigation(
        route = FreshNewsRoutes.AUTH_GRAPH_ROUTE,
        startDestination = FreshNewsRoutes.AUTH_SCREEN_ROUTE,
    ) {
        composable(route = FreshNewsRoutes.AUTH_SCREEN_ROUTE) {
            AuthScreen(navController = navController)
        }
    }
}
