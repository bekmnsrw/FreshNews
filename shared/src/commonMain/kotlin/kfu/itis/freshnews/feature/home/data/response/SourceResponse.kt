package kfu.itis.freshnews.feature.home.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String?
)
