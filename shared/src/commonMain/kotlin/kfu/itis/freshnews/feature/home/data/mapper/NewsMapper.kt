package kfu.itis.freshnews.feature.home.data.mapper

import kfu.itis.freshnews.feature.home.data.datasource.remote.response.ArticleResponse
import kfu.itis.freshnews.feature.home.domain.model.Article

fun ArticleResponse.toArticle(): Article = Article(
    description = description ?: "",
    publishedAt = publishedAt ?: "",
    source = sourceResponse?.name ?: "",
    title = title ?: "",
    urlToImage = urlToImage ?: "",
)

fun List<ArticleResponse>.toArticles(): List<Article> = this
    .map { articleResponse -> articleResponse.toArticle() }
