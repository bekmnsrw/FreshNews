package kfu.itis.freshnews.feature.details.data.mapper

import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.home.domain.model.Article

fun ArticleDetails.toFavoritesArticle(): FavoritesArticle = FavoritesArticle(
    id = null,
    imageUrl = urlToImage,
    title = title,
    description = description,
    source = source,
    publishedAt = publishedAt,
)

fun Article.toArticleDetails(): ArticleDetails = ArticleDetails(
    description = description,
    publishedAt = publishedAt,
    source = source,
    title = title,
    urlToImage = urlToImage,
)
