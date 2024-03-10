package kfu.itis.freshnews.feature.home.domain.model

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceResponse: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)
