package kfu.itis.freshnews.feature.favorites.domain

import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun addFavoritesArticle(articleDetails: ArticleDetails, userId: Long)
    suspend fun removeFavoritesArticle(title: String, userId: Long)
    fun getAllFavoritesArticle(userId: Long): Flow<List<FavoritesArticle>>
    fun getFavoritesArticleById(articleId: Long, userId: Long): Flow<FavoritesArticle>
    fun getFavoritesArticleByTitle(title: String, userId: Long): Flow<FavoritesArticle?>
}
