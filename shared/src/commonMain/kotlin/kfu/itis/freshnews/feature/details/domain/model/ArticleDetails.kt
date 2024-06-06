package kfu.itis.freshnews.feature.details.domain.model

data class ArticleDetails(
    val descr: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val urlToImage: String,
)
