package kfu.itis.freshnews.feature.home.data.datasource.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SourceResponse(
    @SerialName("name") val name: String?,
)
