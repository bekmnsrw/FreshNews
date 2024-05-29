package kfu.itis.freshnews.feature.details.data.datasource.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import kfu.itis.freshnews.feature.details.data.datasource.remote.response.DetailsResponse

internal class RemoteDetailsDataSource(
    private val httpClient: HttpClient,
) {

    /**
     * API documentation: [everything](https://newsapi.org/docs/endpoints/everything)
     */
    suspend fun getArticleDetails(title: String): DetailsResponse = httpClient.get {
        url {
            path("everything")
            parameter("q", title)
            parameter("searchIn", "title")
        }
    }.body()
}
