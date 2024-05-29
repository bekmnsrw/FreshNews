package kfu.itis.freshnews.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import kfu.itis.freshnews.android.navigation.NavigationHost
import kfu.itis.freshnews.android.theme.FreshNewsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FreshNewsTheme {
                CompositionLocalProvider {
                    NavigationHost()
                }
            }
        }
    }
}
