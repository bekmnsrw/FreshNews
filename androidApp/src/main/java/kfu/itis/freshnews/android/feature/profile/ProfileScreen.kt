package kfu.itis.freshnews.android.feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ProfileScreen(
    navController: NavController,
) {
    Column {
        Text(text = "Hello, Profile!")
    }
}
