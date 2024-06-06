package kfu.itis.freshnews.feature.details.data.mapper

import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.home.domain.model.Article

fun ArticleDetails.toFavoritesArticle(userId: Long): FavoritesArticle = FavoritesArticle(
    id = null,
    imageUrl = urlToImage,
    title = title,
    descr = descr,
    source = source,
    publishedAt = publishedAt,
    profileId = userId,
)

fun Article.toArticleDetails(): ArticleDetails = ArticleDetails(
    descr = descr,
    publishedAt = publishedAt,
    source = source,
    title = title,
    urlToImage = urlToImage,
)
