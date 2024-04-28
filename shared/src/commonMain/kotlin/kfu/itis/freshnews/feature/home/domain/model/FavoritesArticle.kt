package kfu.itis.freshnews.feature.home.domain.model

data class FavoritesArticle(
    val id: Int?,
    val imageUrl: String,
    val title: String,
    val description: String,
    val source: String,
    val publishedAt: String,
)
