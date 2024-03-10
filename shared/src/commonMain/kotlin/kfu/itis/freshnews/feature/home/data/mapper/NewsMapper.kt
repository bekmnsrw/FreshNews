package kfu.itis.freshnews.feature.home.data.mapper

import kfu.itis.freshnews.feature.home.data.response.ArticleResponse
import kfu.itis.freshnews.feature.home.data.response.NewsResponse
import kfu.itis.freshnews.feature.home.data.response.SourceResponse
import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.News
import kfu.itis.freshnews.feature.home.domain.model.Source

fun NewsResponse.toNews(): News = News(
    articles = articleResponses?.toArticles() ?: listOf(),
    status = status ?: "",
    totalResults = totalResults ?: 0
)

fun ArticleResponse.toArticle(): Article = Article(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    publishedAt = publishedAt ?: "",
    sourceResponse = sourceResponse?.toSource() ?: Source(id = "", name = ""),
    title = title ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: ""
)

fun List<ArticleResponse>.toArticles(): List<Article> = this.map {
    it.toArticle()
}

fun SourceResponse.toSource(): Source = Source(
    id = id ?: "",
    name = name ?: ""
)
