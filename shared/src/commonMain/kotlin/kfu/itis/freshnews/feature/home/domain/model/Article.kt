package kfu.itis.freshnews.feature.home.domain.model

data class Article(
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val urlToImage: String,
)
