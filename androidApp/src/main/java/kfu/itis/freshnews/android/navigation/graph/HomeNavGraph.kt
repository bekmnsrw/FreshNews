package kfu.itis.freshnews.android.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import kfu.itis.freshnews.android.feature.details.DetailsScreen
import kfu.itis.freshnews.android.feature.home.HomeScreen
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes
import kfu.itis.freshnews.android.navigation.NestedScreen

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(
        route = FreshNewsRoutes.HOME_GRAPH_ROUTE,
        startDestination = FreshNewsRoutes.HOME_SCREEN_ROUTE,
    ) {
        composable(route = FreshNewsRoutes.HOME_SCREEN_ROUTE) {
            HomeScreen(navController = navController)
        }
        composable(
            route = NestedScreen.DetailsScreen.route,
            arguments = listOf(navArgument(NestedScreen.DetailsScreen.argKey) { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                title = backStackEntry.arguments?.getString(NestedScreen.DetailsScreen.argKey).orEmpty(),
            )
        }
    }
}
