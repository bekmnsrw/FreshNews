package kfu.itis.freshnews.feature.home.data.mapper

import kfu.itis.freshnews.FavoritesNews
import kfu.itis.freshnews.feature.home.data.datasource.remote.response.ArticleResponse
import kfu.itis.freshnews.feature.home.data.datasource.remote.response.SourceResponse
import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.home.domain.model.Source

fun ArticleResponse.toArticle(): Article = Article(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    publishedAt = publishedAt ?: "",
    source = sourceResponse?.toSource() ?: Source(id = "", name = ""),
    title = title ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
)

fun List<ArticleResponse>.toArticles(): List<Article> = this.map {
    it.toArticle()
}

fun SourceResponse.toSource(): Source = Source(
    id = id ?: "",
    name = name ?: "",
)

fun FavoritesNews.toFavoritesArticle(): FavoritesArticle = FavoritesArticle(
    id = id.toInt(),
    imageUrl = image_url,
    title = title,
    description = description,
    source = source,
    publishedAt = published_at,
)

fun List<FavoritesNews>.toFavoritesArticleList(): List<FavoritesArticle> = this.map {
    it.toFavoritesArticle()
}
