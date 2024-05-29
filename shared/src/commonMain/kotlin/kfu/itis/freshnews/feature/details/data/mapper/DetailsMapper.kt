package kfu.itis.freshnews.feature.details.data.mapper

import kfu.itis.freshnews.feature.details.data.datasource.remote.response.ArticleDetailsResponse
import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle

fun ArticleDetailsResponse.toArticleDetails(): ArticleDetails = ArticleDetails(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    publishedAt = publishedAt ?: "",
    source = sourceResponse?.name ?: "",
    title = title ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
)

fun ArticleDetails.toFavoritesArticle(): FavoritesArticle = FavoritesArticle(
    id = null,
    imageUrl = urlToImage,
    title = title,
    content = content,
    description = description,
    source = source,
    publishedAt = publishedAt,
)
