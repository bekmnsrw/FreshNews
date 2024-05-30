package kfu.itis.freshnews.feature.home.data.datasource.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ArticleResponse(
    @SerialName("description") val description: String?,
    @SerialName("publishedAt") val publishedAt: String?,
    @SerialName("source") val sourceResponse: SourceResponse?,
    @SerialName("title") val title: String?,
    @SerialName("urlToImage") val urlToImage: String?,
)
