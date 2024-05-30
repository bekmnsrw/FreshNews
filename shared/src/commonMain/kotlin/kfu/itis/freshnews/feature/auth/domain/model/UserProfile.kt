package kfu.itis.freshnews.feature.auth.domain.model

data class UserProfile(
    val id: Int?,
    val login: String,
    val password: String,
)
