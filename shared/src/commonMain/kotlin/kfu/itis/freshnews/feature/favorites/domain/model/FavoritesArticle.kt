package kfu.itis.freshnews.feature.favorites.domain.model

data class FavoritesArticle(
    val id: Long?,
    val imageUrl: String,
    val title: String,
    val descr: String,
    val source: String,
    val publishedAt: String,
    val profileId: Long,
)
