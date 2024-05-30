package kfu.itis.freshnews.feature.favorites.data.mapper

import kfu.itis.freshnews.FavoritesNews
import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle

fun FavoritesNews.toFavoritesArticle(): FavoritesArticle = FavoritesArticle(
    id = id.toInt(),
    imageUrl = image_url,
    title = title,
    description = description,
    source = source,
    publishedAt = published_at,
)

fun List<FavoritesNews>.toFavoritesArticleList(): List<FavoritesArticle> = this
    .map { favoritesNews -> favoritesNews.toFavoritesArticle() }

fun FavoritesArticle.toArticleDetails(): ArticleDetails = ArticleDetails(
    description = description,
    publishedAt = publishedAt,
    source = source,
    title = title,
    urlToImage = imageUrl,
)
