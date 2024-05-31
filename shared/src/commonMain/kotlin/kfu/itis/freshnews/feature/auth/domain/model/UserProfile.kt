package kfu.itis.freshnews.feature.auth.domain.model

data class UserProfile(
    val id: Long?,
    val login: String,
    val password: String,
)
