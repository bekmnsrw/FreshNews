package kfu.itis.freshnews.android.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kfu.itis.freshnews.android.navigation.bottombar.BottomBar
import kfu.itis.freshnews.android.navigation.bottombar.TabItem
import kfu.itis.freshnews.android.navigation.graph.authNavGraph
import kfu.itis.freshnews.android.navigation.graph.favoritesNavGraph
import kfu.itis.freshnews.android.navigation.graph.homeNavGraph
import kfu.itis.freshnews.android.navigation.graph.profileNavGraph
import kfu.itis.freshnews.android.navigation.graph.splashNavGraph

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun NavigationHost() {
    val navController: NavHostController = rememberNavController()
    val currentSelectedScreen by navController.currentScreenAsState()
    val currentRoute by navController.currentRouteAsState()

    val tabItemRoutes = listOf(
        FreshNewsRoutes.HOME_SCREEN_ROUTE,
        FreshNewsRoutes.FAVORITES_SCREEN_ROUTE,
        FreshNewsRoutes.PROFILE_SCREEN_ROUTE,
    )

    Scaffold(
        bottomBar = {
            if (currentRoute == null || tabItemRoutes.contains(currentRoute)) {
                BottomBar(
                    navController = navController,
                    currentSelectedScreen = currentSelectedScreen,
                )
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = FreshNewsRoutes.SPLASH_GRAPH_ROUTE,
        ) {
            splashNavGraph(navController)
            authNavGraph(navController)
            homeNavGraph(navController)
            favoritesNavGraph(navController)
            profileNavGraph(navController)
        }
    }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<TabItem> {
    val selectedItem = remember { mutableStateOf<TabItem>(TabItem.Home) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == TabItem.Home.route } -> {
                    selectedItem.value = TabItem.Home
                }
                destination.hierarchy.any { it.route == TabItem.Favorite.route } -> {
                    selectedItem.value = TabItem.Favorite
                }
                destination.hierarchy.any { it.route == TabItem.Profile.route } -> {
                    selectedItem.value = TabItem.Profile
                }
            }
        }
        addOnDestinationChangedListener(listener)
        onDispose { removeOnDestinationChangedListener(listener) }
    }
    return selectedItem
}

@Stable
@Composable
private fun NavController.currentRouteAsState(): State<String?> {
    val selectedItem = remember { mutableStateOf<String?>(null) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            selectedItem.value = destination.route
        }
        addOnDestinationChangedListener(listener)
        onDispose { removeOnDestinationChangedListener(listener) }
    }
    return selectedItem
}
