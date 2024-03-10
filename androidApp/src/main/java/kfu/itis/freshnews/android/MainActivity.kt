package kfu.itis.freshnews.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kfu.itis.freshnews.Greeting
import kfu.itis.freshnews.PlatformSDK
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesUseCase
import kotlinx.coroutines.launch
import org.kodein.di.instance

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getTopHeadlinesUseCase: GetTopHeadlinesUseCase? = PlatformSDK._di?.instance<GetTopHeadlinesUseCase>()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val coroutineScope = rememberCoroutineScope()

                    GreetingView(
                        text = Greeting().greet(),
                        onClick = {
                            coroutineScope.launch {
                                getTopHeadlinesUseCase?.invoke()?.articles?.forEach {
                                    println(it.title)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingView(
    text: String,
    onClick: () -> Unit
) {
    Column {
        Text(text = text)
        Button(onClick = onClick) {
            Text(text = "Get Top Headlines")
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView(
            text = "Hello, Android!",
            onClick = {}
        )
    }
}
