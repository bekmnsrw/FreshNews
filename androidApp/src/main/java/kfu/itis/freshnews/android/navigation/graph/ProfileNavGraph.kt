package kfu.itis.freshnews.android.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kfu.itis.freshnews.android.feature.profile.ProfileScreen
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes

fun NavGraphBuilder.profileNavGraph(navController: NavController) {
    navigation(
        route = FreshNewsRoutes.PROFILE_GRAPH_ROUTE,
        startDestination = FreshNewsRoutes.PROFILE_SCREEN_ROUTE,
    ) {
        composable(route = FreshNewsRoutes.PROFILE_SCREEN_ROUTE) {
            ProfileScreen(navController = navController)
        }
    }
}
