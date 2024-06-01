package kfu.itis.freshnews.android.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kfu.itis.freshnews.android.feature.splash.SplashScreen
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes

fun NavGraphBuilder.splashNavGraph(
    navController: NavController,
) {
    navigation(
        route = FreshNewsRoutes.SPLASH_GRAPH_ROUTE,
        startDestination = FreshNewsRoutes.SPLASH_SCREEN_ROUTE,
    ) {
        composable(route = FreshNewsRoutes.SPLASH_SCREEN_ROUTE) {
            SplashScreen(navController = navController)
        }
    }
}
