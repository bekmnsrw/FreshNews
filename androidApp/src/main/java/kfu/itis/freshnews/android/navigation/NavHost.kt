package kfu.itis.freshnews.android.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun NavHost(
    content: @Composable () -> Unit = {},
) {

    Scaffold(
        bottomBar = { BottomBar() },
        content = { content() },
    )
}
