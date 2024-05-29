package kfu.itis.freshnews.feature.home.data.datasource.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NewsResponse(
    @SerialName("articles") val articleResponses: List<ArticleResponse>,
)
