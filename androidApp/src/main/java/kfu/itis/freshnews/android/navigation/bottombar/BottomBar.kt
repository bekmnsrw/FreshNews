package kfu.itis.freshnews.android.navigation.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kfu.itis.freshnews.android.theme.ThemeProvider

@Composable
fun BottomBar(
    navController: NavHostController,
    currentSelectedScreen: TabItem,
) {
    val tabItems = listOf(
        TabItem.Home,
        TabItem.Favorite,
        TabItem.Profile,
    )

    NavigationBar(containerColor = ThemeProvider.colors.bottomBarContainer) {
        tabItems.forEach { tabItem ->
            NavigationBarItem(
                selected = tabItem == currentSelectedScreen,
                onClick = { navController.navigateToRootScreen(tabItem) },
                icon = { Icon(tabItem.icon, null) },
                label = { Text(stringResource(id = tabItem.name)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ThemeProvider.colors.accent,
                    selectedTextColor = ThemeProvider.colors.accent,
                    unselectedIconColor = ThemeProvider.colors.bottomBarItemUnselected,
                    unselectedTextColor = ThemeProvider.colors.bottomBarItemUnselected,
                )
            )
        }
    }
}

private fun NavController.navigateToRootScreen(rootScreen: TabItem) {
    navigate(rootScreen.route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
