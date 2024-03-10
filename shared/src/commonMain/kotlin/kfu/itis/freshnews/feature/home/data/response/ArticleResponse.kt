package kfu.itis.freshnews.feature.home.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("author") val author: String?,
    @SerialName("content") val content: String?,
    @SerialName("description") val description: String?,
    @SerialName("publishedAt") val publishedAt: String?,
    @SerialName("source") val sourceResponse: SourceResponse?,
    @SerialName("title") val title: String?,
    @SerialName("url") val url: String?,
    @SerialName("urlToImage") val urlToImage: String?
)
