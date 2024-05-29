package kfu.itis.freshnews.feature.details.data.datasource.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DetailsResponse(
    @SerialName("articles") val articleDetails: List<ArticleDetailsResponse>,
)
