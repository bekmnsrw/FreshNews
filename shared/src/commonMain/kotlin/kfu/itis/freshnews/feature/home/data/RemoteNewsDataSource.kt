package kfu.itis.freshnews.feature.home.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import kfu.itis.freshnews.feature.home.data.response.NewsResponse

internal class RemoteNewsDataSource(
    private val httpClient: HttpClient
) {

    /*
     * API details [here](https://newsapi.org/docs/endpoints/top-headlines)
     */
    suspend fun getTopHeadlines(): NewsResponse = httpClient.get {
        url {
            path("top-headlines")
            parameter("country", "us")
        }
    }.body()
}
