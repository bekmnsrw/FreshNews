package kfu.itis.freshnews.android.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kfu.itis.freshnews.android.theme.ThemeProvider

@Composable
fun BottomBar(
    bottomBarItems: List<BottomBarItem> = listOf(
        BottomBarItem.Home,
        BottomBarItem.Favorite,
        BottomBarItem.Profile,
    ),
    startDestination: BottomBarItem = BottomBarItem.Home,
) {

    NavigationBar(
        containerColor = ThemeProvider.colors.bottomBarContainer,
    ) {
        bottomBarItems.forEach { bottomBarItem ->
            NavigationBarItem(
                selected = bottomBarItem == startDestination,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = bottomBarItem.icon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = bottomBarItem.name),
                    )
                },
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
