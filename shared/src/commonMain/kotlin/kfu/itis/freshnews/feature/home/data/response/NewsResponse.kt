package kfu.itis.freshnews.feature.home.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("articles") val articleResponses: List<ArticleResponse>,
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Int
)
