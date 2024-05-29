package kfu.itis.freshnews.feature.details.data.datasource.remote.response

import kfu.itis.freshnews.feature.home.data.datasource.remote.response.SourceResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ArticleDetailsResponse(
    @SerialName("author") val author: String?,
    @SerialName("content") val content: String?,
    @SerialName("description") val description: String?,
    @SerialName("publishedAt") val publishedAt: String?,
    @SerialName("source") val sourceResponse: SourceResponse?,
    @SerialName("title") val title: String?,
    @SerialName("url") val url: String?,
    @SerialName("urlToImage") val urlToImage: String?,
)
