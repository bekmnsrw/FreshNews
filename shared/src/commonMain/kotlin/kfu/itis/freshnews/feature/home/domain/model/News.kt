package kfu.itis.freshnews.feature.home.domain.model

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
