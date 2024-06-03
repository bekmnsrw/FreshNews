package kfu.itis.freshnews.android.navigation.graph

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kfu.itis.freshnews.android.feature.details.DetailsScreen
import kfu.itis.freshnews.android.feature.home.HomeScreen
import kfu.itis.freshnews.android.navigation.FreshNewsRoutes
import kfu.itis.freshnews.android.navigation.NestedScreen
import kfu.itis.freshnews.feature.details.data.mapper.toArticleDetails
import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.presentation.HomeViewModel

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    homeViewModel: HomeViewModel,
) {
    navigation(
        route = FreshNewsRoutes.HOME_GRAPH_ROUTE,
        startDestination = FreshNewsRoutes.HOME_SCREEN_ROUTE,
    ) {
        composable(route = FreshNewsRoutes.HOME_SCREEN_ROUTE) {
            HomeScreen(
                viewModel = homeViewModel,
                navController = navController,
                snackbarHostState = snackbarHostState,
            )
        }
        composable(NestedScreen.DetailsScreenFromHome.route) {
            DetailsScreen(
                navController = navController,
                articleDetails = navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Article>(NestedScreen.DetailsScreenFromHome.argKey)
                    ?.toArticleDetails(),
                snackbarHostState = snackbarHostState,
            )
        }
    }
}
