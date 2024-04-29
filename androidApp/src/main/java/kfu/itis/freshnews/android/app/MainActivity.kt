package kfu.itis.freshnews.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kfu.itis.freshnews.android.feature.home.HomeScreen
import kfu.itis.freshnews.android.navigation.NavHost
import kfu.itis.freshnews.android.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                NavHost(
                    content = { HomeScreen() },
                )
            }
        }
    }
}
