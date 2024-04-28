package kfu.itis.freshnews.feature.home.domain

import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory
import kfu.itis.freshnews.feature.home.domain.model.FavoritesArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopHeadlines(): List<Article>
    suspend fun getTopHeadlinesByCategory(category: ArticleCategory): List<Article>
    suspend fun searchTopHeadlinesByPhrase(phrase: String): List<Article>
    suspend fun addFavoritesArticle(favoritesArticle: FavoritesArticle)
    suspend fun removeFavoritesArticle(id: Int)
    suspend fun getAllFavoritesArticle(): Flow<List<FavoritesArticle>>
}
