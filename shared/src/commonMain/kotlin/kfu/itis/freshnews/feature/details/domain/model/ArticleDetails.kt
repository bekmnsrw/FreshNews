package kfu.itis.freshnews.feature.details.domain.model

data class ArticleDetails(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String,
)
