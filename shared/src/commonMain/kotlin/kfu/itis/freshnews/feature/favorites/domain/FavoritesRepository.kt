package kfu.itis.freshnews.feature.favorites.domain

import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun addFavoritesArticle(articleDetails: ArticleDetails)
    suspend fun removeFavoritesArticle(id: Int)
    fun getAllFavoritesArticle(): Flow<List<FavoritesArticle>>
    fun getFavoritesArticleById(id: Int): Flow<FavoritesArticle>
    fun getFavoritesArticleByTitle(title: String): Flow<FavoritesArticle?>
}
