package kfu.itis.freshnews.android.navigation.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kfu.itis.freshnews.android.designsystem.theme.ThemeProvider

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

    NavigationBar(containerColor = ThemeProvider.colors.bottomBar) {
        tabItems.forEach { tabItem ->
            NavigationBarItem(
                selected = tabItem == currentSelectedScreen,
                onClick = {
                    navController.navigate(tabItem.route.lowercase()) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(tabItem.icon, null) },
                label = { Text(stringResource(id = tabItem.name)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ThemeProvider.colors.bottomBarSelected,
                    selectedTextColor = ThemeProvider.colors.bottomBarSelected,
                    unselectedIconColor = ThemeProvider.colors.bottomBarUnselected,
                    unselectedTextColor = ThemeProvider.colors.bottomBarUnselected,
                )
            )
        }
    }
}
