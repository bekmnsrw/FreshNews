package kfu.itis.freshnews.feature.home.data.datasource.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import kfu.itis.freshnews.feature.home.data.datasource.remote.response.NewsResponse
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory

internal class RemoteNewsDataSource(
    private val httpClient: HttpClient,
) {

    /**
     * API documentation: [top-headlines](https://newsapi.org/docs/endpoints/top-headlines)
     */
    suspend fun getTopHeadlines(): NewsResponse = httpClient.get {
        url {
            path("top-headlines")
            parameter("category", "general")
            parameter("country", "us")
        }
    }.body()

    suspend fun getTopHeadlinesByCategory(category: ArticleCategory): NewsResponse = httpClient.get {
        url {
            path("top-headlines")
            parameter("category", category.name.lowercase())
            parameter("country", "us")
        }
    }.body()

    suspend fun searchTopHeadlinesByPhrase(phrase: String): NewsResponse = httpClient.get {
        url {
            path("top-headlines")
            parameter("q", phrase)
            parameter("country", "us")
        }
    }.body()
}
