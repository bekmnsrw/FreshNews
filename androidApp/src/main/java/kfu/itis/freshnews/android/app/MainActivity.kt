package kfu.itis.freshnews.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kfu.itis.freshnews.android.navigation.NavigationHost
import kfu.itis.freshnews.android.designsystem.theme.FreshNewsTheme

class MainActivity : ComponentActivity() {

    private val appSettingsViewModel by viewModels<AppSettings>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val appSettings = remember { appSettingsViewModel }
            val currentAppSettings = appSettings.state.collectAsStateWithLifecycle().value

            FreshNewsTheme(
                darkTheme = currentAppSettings.isDarkModeEnabled,
            ) {
                CompositionLocalProvider(
                    LocalAppSettings provides appSettings
                ) {
                    NavigationHost(appSettings)
                }
            }
        }
    }
}

val LocalAppSettings = staticCompositionLocalOf<AppSettings> { error("No AppSettings provided") }
